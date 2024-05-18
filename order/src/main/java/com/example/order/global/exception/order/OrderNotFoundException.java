package com.example.order.global.exception.order;

import com.example.order.global.exception.common.CustomException;
import com.example.order.global.exception.common.ErrorCode;

public class OrderNotFoundException extends CustomException {
public OrderNotFoundException(){super(ErrorCode.ORDER_NOT_FOUND_EXCEPTION);}
}
