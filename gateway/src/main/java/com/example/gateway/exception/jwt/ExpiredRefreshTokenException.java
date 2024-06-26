package com.example.gateway.exception.jwt;


import com.example.gateway.exception.common.CustomException;
import com.example.gateway.exception.common.ErrorCode;

public class ExpiredRefreshTokenException extends CustomException {

    public ExpiredRefreshTokenException() {
        super(ErrorCode.EXPIRED_REFRESH_TOKEN_EXCEPTION);
    }
}
