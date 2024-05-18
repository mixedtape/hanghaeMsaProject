package com.example.gateway.jwt;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final String jwt;

    public JwtAuthenticationToken(String username, String jwt) {
        super(username, null, null);
        this.jwt = jwt;
    }

}