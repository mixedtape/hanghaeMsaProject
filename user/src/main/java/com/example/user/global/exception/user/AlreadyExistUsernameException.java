package com.example.user.global.exception.user;


import com.example.user.global.exception.common.CustomException;
import com.example.user.global.exception.common.ErrorCode;

public class AlreadyExistUsernameException extends CustomException {

    public AlreadyExistUsernameException() {
        super(ErrorCode.ALREADY_EXIST_USERNAME_EXCEPTION);
    }
}
