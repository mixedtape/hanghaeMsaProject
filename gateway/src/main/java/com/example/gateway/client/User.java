package com.example.gateway.client;


import com.example.gateway.auditing.BaseTimeEntity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {


    private Long id;


    private String username;


    private String password;

    private String email;

    private String phoneNum;


    private boolean emailVerified = false;


    private boolean is_deleted = false;

    private LocalDateTime deletedAt;


    private String address;



    private String emailVerificationCode;




}
