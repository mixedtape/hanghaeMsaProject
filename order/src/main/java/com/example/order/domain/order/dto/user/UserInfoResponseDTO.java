package com.example.order.domain.order.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponseDTO {

    private Long userId;
    private String username;
    private String email;
    private String description;
    private boolean emailVerified;
    private String address;
}
