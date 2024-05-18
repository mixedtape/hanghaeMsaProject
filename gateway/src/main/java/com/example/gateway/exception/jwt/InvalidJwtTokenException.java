package com.example.gateway.exception.jwt;


import com.example.gateway.exception.common.CustomException;
import com.example.gateway.exception.common.ErrorCode;

public class InvalidJwtTokenException extends CustomException {

    public InvalidJwtTokenException(Throwable cause) {
        super(ErrorCode.INVALID_JWT_TOKEN_EXCEPTION, cause);
    }

    public InvalidJwtTokenException() {
        super(ErrorCode.INVALID_JWT_TOKEN_EXCEPTION);
    }
}
