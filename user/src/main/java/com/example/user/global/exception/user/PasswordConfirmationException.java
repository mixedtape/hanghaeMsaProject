package com.example.user.global.exception.user;


import com.example.user.global.exception.common.CustomException;
import com.example.user.global.exception.common.ErrorCode;

public class PasswordConfirmationException extends CustomException {

    public PasswordConfirmationException() {
        super(ErrorCode.PASSWORD_CONFIRMATION_EXCEPTION);
    }
}
