package com.example.user.global.exception.user;


import com.example.user.global.exception.common.CustomException;
import com.example.user.global.exception.common.ErrorCode;

public class EmailServiceException extends CustomException {
    public EmailServiceException() {
        super(ErrorCode.EMAIL_SERVICE_EXCEPTION);
    }
}
