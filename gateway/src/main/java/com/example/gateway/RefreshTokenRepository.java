package com.example.gateway;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepository {
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public RefreshTokenRepository(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveRefreshToken(String username, String refreshToken, long validity) {
        redisTemplate.opsForValue().set(username, refreshToken, validity, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(String username) {
        return redisTemplate.opsForValue().get(username);
    }

    public void deleteRefreshToken(String username) {
        redisTemplate.delete(username);
    }
}