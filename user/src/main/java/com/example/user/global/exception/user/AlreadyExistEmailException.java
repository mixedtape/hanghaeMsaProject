package com.example.user.global.exception.user;


import com.example.user.global.exception.common.CustomException;
import com.example.user.global.exception.common.ErrorCode;

public class AlreadyExistEmailException extends CustomException {

    public AlreadyExistEmailException() {
        super(ErrorCode.ALREADY_EXIST_EMAIL_EXCEPTION);
    }
}
