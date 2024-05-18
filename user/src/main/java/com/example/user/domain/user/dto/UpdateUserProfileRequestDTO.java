package com.example.user.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserProfileRequestDTO {

    private String phoneNum;

    private String address;
}
