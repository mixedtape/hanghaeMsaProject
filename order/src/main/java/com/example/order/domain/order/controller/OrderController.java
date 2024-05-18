package com.example.order.domain.order.controller;

import com.example.order.domain.order.dto.OrderResponseDTO;
import com.example.order.domain.order.dto.UpdateOrderRequestDTO;
import com.example.order.domain.order.service.OrderService;
import com.example.order.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{wishListId}")
    public ResponseEntity<CommonResponse<Void>> createOrder(
            @PathVariable(name = "wishListId") Long wishListId
    ) {
        orderService.createOrder(wishListId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.of("주문 생성 성공", null));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CommonResponse<OrderResponseDTO>> getOrder(
            @PathVariable(name = "orderId") Long orderId
    ) {
        OrderResponseDTO orderResponseDTO = orderService.getOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("주문 조회 성공", orderResponseDTO));
    }


    @PatchMapping
    public ResponseEntity<CommonResponse<Void>> updateOrderState(
            @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO
    ) {
        orderService.updateOrderState(updateOrderRequestDTO.getOrderId(),
                updateOrderRequestDTO.getOrderState());
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("주문 상태 업데이트 성공", null));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<CommonResponse<Void>> deleteOrder(
            @PathVariable(name = "orderId") Long orderId
    ) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("주문 삭제 성공", null));
    }
}
