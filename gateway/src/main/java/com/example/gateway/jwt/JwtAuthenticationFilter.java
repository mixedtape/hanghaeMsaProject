package com.example.gateway.jwt;

import com.example.gateway.exception.jwt.ExpiredJwtTokenException;
import com.example.gateway.exception.jwt.InvalidJwtSignatureException;
import com.example.gateway.exception.jwt.InvalidJwtTokenException;
import com.example.gateway.exception.jwt.UnsupportedJwtTokenException;
import io.jsonwebtoken.Claims;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // Specify the URLs that should be excluded from JWT token validation
        Predicate<ServerHttpRequest> isPublicUrl = r -> {
            List<String> publicUrls = List.of("/auth/login", "/auth/refresh");
            return publicUrls.stream().anyMatch(r.getURI().getPath()::contains);
        };

        if (isPublicUrl.test(request)) {
            return chain.filter(exchange);
        }

        String authorizationHeader = request.getHeaders().getFirst("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        String token = authorizationHeader.substring(7);
        try {
            Claims claims = (Claims) jwtUtil.getUserInfoFromToken(token);
            String username = claims.getSubject();
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, null);

            // Set Authentication in SecurityContext
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);
            // Additional validation or processing with user claims
        } catch (InvalidJwtSignatureException | ExpiredJwtTokenException |
                 UnsupportedJwtTokenException | InvalidJwtTokenException e) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }
}