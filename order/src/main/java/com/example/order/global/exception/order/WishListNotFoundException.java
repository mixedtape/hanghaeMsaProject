package com.example.order.global.exception.order;

import com.example.order.global.exception.common.CustomException;
import com.example.order.global.exception.common.ErrorCode;

public class WishListNotFoundException extends CustomException {
    public WishListNotFoundException(){super(ErrorCode.WISHLIST_NOT_FOUND_EXCEPTION);}

}
