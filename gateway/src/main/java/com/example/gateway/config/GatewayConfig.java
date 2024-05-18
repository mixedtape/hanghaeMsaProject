package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("books", r -> r.path("/api/books/**")
                        .uri("http://localhost:7070"))
                .route("users", r -> r.path("/api/users/**")
                        .uri("http://localhost:9090"))
                .build();
    }


}