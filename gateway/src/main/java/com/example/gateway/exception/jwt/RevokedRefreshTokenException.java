package com.example.gateway.exception.jwt;


import com.example.gateway.exception.common.CustomException;
import com.example.gateway.exception.common.ErrorCode;

public class RevokedRefreshTokenException extends CustomException {

    public RevokedRefreshTokenException() {
        super(ErrorCode.REVOKED_REFRESH_TOKEN_EXCEPTION);
    }
}
