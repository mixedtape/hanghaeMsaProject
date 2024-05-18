package com.example.user.global.exception.user;


import com.example.user.global.exception.common.CustomException;
import com.example.user.global.exception.common.ErrorCode;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super(ErrorCode.NOT_FOUND_USER_EXCEPTION);
    }
}
