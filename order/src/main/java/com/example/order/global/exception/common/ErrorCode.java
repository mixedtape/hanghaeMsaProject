package com.example.order.global.exception.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //jwt
    INVALID_JWT_SIGNATURE_EXCEPTION(401, "유효하지 않는 JWT 서명 입니다."),
    EXPIRED_JWT_TOKEN_EXCEPTION(401, "만료된 JWT token 입니다."),
    UNSUPPORTED_JWT_TOKEN_EXCEPTION(401, "지원되지 않는 JWT 토큰 입니다."),
    INVALID_JWT_TOKEN_EXCEPTION(401, "잘못된 JWT 토큰 입니다."),
    REFRESH_TOKEN_NOT_FOUND_EXCEPTION(404, "Refresh Token을 찾을 수 없습니다."),
    EXPIRED_REFRESH_TOKEN_EXCEPTION(401, "만료된 Refresh Token 입니다."),
    REVOKED_REFRESH_TOKEN_EXCEPTION(401, "사용이 중지된 Refresh Token 입니다."),
    //user
    NOT_FOUND_USER_EXCEPTION(400, "해당 유저가 존재하지 않습니다"),
    PASSWORD_CONFIRMATION_EXCEPTION(400, "비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    PASSWORD_MISMATCH_EXCEPTION(400, "비밀번호가 일치하지 않습니다."),
    ALREADY_EXIST_USERNAME_EXCEPTION(400, "중복된 유저네임입니다."),
    ALREADY_EXIST_EMAIL_EXCEPTION(400, "중복된 이메일입니다."),
    AUTHENTICATION_MISMATCH_EXCEPTION(401, "권한이 없습니다."),

    //wishlist
    ALREADY_EXIST_WISHLIST_EXCEPTION(401,"이미 위시 리스트가 존재하는 유저입니다"),
    WISHLIST_NOT_FOUND_EXCEPTION(401,"존재하지 않는 위시 리스트입니다"),
    //order
    ORDER_NOT_FOUND_EXCEPTION(401,"존재하지 않는 주문 입니다"),


    //email
    EMAIL_SERVICE_EXCEPTION(401,"이메일 발송에 실패 하였 습니다"),
    INVALID_EMAIL_CODE_EXCEPTION(401,"이메일 인증 코드가 틀립 니다.");
    private final int statusCode;

    private final String message;

    ErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}