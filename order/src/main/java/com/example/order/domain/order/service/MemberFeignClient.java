package com.example.order.domain.order.service;

import com.example.user.domain.user.dto.UserInfoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:9090/api/users")
public interface MemberFeignClient {

    @GetMapping("/{userId}")
    UserInfoResponseDTO getProfile(@PathVariable("userId") Long userId);
}