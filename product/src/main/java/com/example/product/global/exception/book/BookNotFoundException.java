package com.example.product.global.exception.book;


import com.example.product.global.exception.common.CustomException;
import com.example.product.global.exception.common.ErrorCode;

public class BookNotFoundException extends CustomException {

    public BookNotFoundException() {
        super(ErrorCode.BOOK_NOT_FOUND_EXCEPTION);
    }

}
