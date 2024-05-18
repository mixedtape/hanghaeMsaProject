package com.example.order.domain.order.dto;

import com.example.order.domain.order.entity.ProductOrder;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long totalPrice;
    private String orderNum;
    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
    private String orderSta0t;
    private String address;

    public static OrderResponseDTO fromEntity(ProductOrder productOrder){
        return new OrderResponseDTO(
                productOrder.getTotalPrice(),
                productOrder.getOrderNumber(),
                productOrder.getOrderTime(),
                productOrder.getDeliveryTime(),
                productOrder.getOrderStat(),
                productOrder.getAddress()
        );
    }

}
