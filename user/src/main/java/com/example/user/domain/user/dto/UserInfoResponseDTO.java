package com.example.user.domain.user.dto;

import com.example.user.domain.user.entity.User;
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

    public UserInfoResponseDTO(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.emailVerified = user.isEmailVerified();
        this.address = user.getAddress();
    }
}
