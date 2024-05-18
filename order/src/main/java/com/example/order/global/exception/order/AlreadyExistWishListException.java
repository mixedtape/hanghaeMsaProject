package com.example.order.global.exception.order;

import com.example.order.global.exception.common.CustomException;
import com.example.order.global.exception.common.ErrorCode;

public class AlreadyExistWishListException extends CustomException {
public AlreadyExistWishListException(){super(ErrorCode.ALREADY_EXIST_WISHLIST_EXCEPTION);}
}
