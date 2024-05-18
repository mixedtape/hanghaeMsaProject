package com.example.order.domain.order.dto;

import lombok.Getter;

@Getter
public class UpdateOrderRequestDTO {
    Long orderId;
    String orderState;

}
