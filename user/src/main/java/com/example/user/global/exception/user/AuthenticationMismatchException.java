package com.example.user.global.exception.user;


import com.example.user.global.exception.common.CustomException;
import com.example.user.global.exception.common.ErrorCode;

public class AuthenticationMismatchException extends CustomException {

    public AuthenticationMismatchException() {
        super(ErrorCode.AUTHENTICATION_MISMATCH_EXCEPTION);
    }
}
