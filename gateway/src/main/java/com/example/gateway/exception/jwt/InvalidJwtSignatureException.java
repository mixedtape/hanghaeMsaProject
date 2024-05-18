package com.example.gateway.exception.jwt;


import com.example.gateway.exception.common.CustomException;
import com.example.gateway.exception.common.ErrorCode;

public class InvalidJwtSignatureException extends CustomException {

    public InvalidJwtSignatureException(Throwable cause) {
        super(ErrorCode.INVALID_JWT_SIGNATURE_EXCEPTION, cause);
    }
}
