package com.example.user.global.exception.user;


import com.example.user.global.exception.common.CustomException;
import com.example.user.global.exception.common.ErrorCode;

public class InvalidPasswordException extends CustomException {

    public InvalidPasswordException() {
        super(ErrorCode.PASSWORD_MISMATCH_EXCEPTION);
    }
}
