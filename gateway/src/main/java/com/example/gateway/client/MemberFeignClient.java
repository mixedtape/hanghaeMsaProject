package com.example.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:9090/api/users",configuration = FeignConfig.class)
public interface MemberFeignClient {

    @GetMapping
    User getUserByName(@RequestParam(value = "userName") String userName);
}
