package com.example.gateway;

import com.example.gateway.client.MemberFeignClient;
import com.example.gateway.client.User;
import com.example.gateway.jwt.JwtUtil;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberFeignClient memberFeignClient;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String userName, @RequestParam String password) {
        User user = memberFeignClient.getUserByName(userName);

        if (passwordEncoder.matches(password, user.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(userName);
            String refreshToken = jwtUtil.generateRefreshToken(userName);

            refreshTokenRepository.saveRefreshToken(userName, refreshToken, jwtUtil.getRefreshTokenValidity());
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);

            return tokens;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestParam String refreshToken) {
        String userName = jwtUtil.extractUsername(refreshToken);

        String savedRefreshToken = refreshTokenRepository.getRefreshToken(userName);
        if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken) && !jwtUtil.isTokenExpired(refreshToken)) {
            String accessToken = jwtUtil.generateAccessToken(userName);
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(tokens);
        } else {
            throw new RuntimeException("Invalid or expired refresh token");
        }
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String userName) {
        refreshTokenRepository.deleteRefreshToken(userName);
    }
}