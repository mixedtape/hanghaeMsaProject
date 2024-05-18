package com.example.product.global.exception.book;


import com.example.product.global.exception.common.CustomException;
import com.example.product.global.exception.common.ErrorCode;

public class AlreadyExistBookException extends CustomException {

    public AlreadyExistBookException() {
        super(ErrorCode.ALREADY_EXIST_BOOK_EXCEPTION);
    }

}
