package com.example.user.global.exception.user;


import com.example.user.global.exception.common.CustomException;
import com.example.user.global.exception.common.ErrorCode;

public class InvalidEmailCodeException extends CustomException {
    public InvalidEmailCodeException() {
        super(ErrorCode.INVALID_EMAIL_CODE_EXCEPTION);
    }
}
