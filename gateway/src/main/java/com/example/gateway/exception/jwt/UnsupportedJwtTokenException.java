package com.example.gateway.exception.jwt;


import com.example.gateway.exception.common.CustomException;
import com.example.gateway.exception.common.ErrorCode;

public class UnsupportedJwtTokenException extends CustomException {

    public UnsupportedJwtTokenException(Throwable cause) {
        super(ErrorCode.UNSUPPORTED_JWT_TOKEN_EXCEPTION, cause);
    }
}
