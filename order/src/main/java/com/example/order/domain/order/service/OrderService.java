package com.example.order.domain.order.service;

import com.example.order.domain.order.dto.OrderResponseDTO;
import com.example.order.domain.order.dto.WishListResponseDTO;
import com.example.order.domain.order.entity.ProductOrder;
import com.example.order.domain.order.entity.WishList;
import com.example.order.domain.order.repository.OrderRepository;
import com.example.order.domain.order.repository.WishListRepository;
import com.example.order.global.exception.order.OrderNotFoundException;
import com.example.order.global.exception.order.WishListNotFoundException;
import com.example.user.domain.user.dto.UserInfoResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WishListRepository wishListRepository;
    private final WishListService wishListService;
    private final MemberFeignClient memberFeignClient;
    private final ProductFeignClient productFeignClient;

    public void createOrder(Long wishListId) {
        WishList wishList = wishListRepository.findById(wishListId).orElseThrow(
                WishListNotFoundException::new);
        UserInfoResponseDTO userInfoResponseDTO = memberFeignClient.getProfile(
                wishList.getUserId());
        Long totalPrice = wishListService.getTotalPrice(wishListId);
        LocalDateTime orderTime = LocalDateTime.now();
        ProductOrder productOrder = ProductOrder.builder()
                .totalPrice(totalPrice)
                .orderTime(orderTime)
                .orderStat("배송 대기중")
                .address(userInfoResponseDTO.getAddress())
                .deliveryTime(orderTime.plusDays(1))
                .build();
        orderRepository.save(productOrder);
        List<WishListResponseDTO> list = wishListService.getProductQuantities(wishList.getUserId());
        System.out.println(list);
        productFeignClient.productDeduction(list);

    }

    public OrderResponseDTO getOrder(Long orderId) {
        ProductOrder productOrder = isOrderExist(orderId);
        return OrderResponseDTO.fromEntity(productOrder);

    }

    @Transactional
    public void updateOrderState(Long orderId, String orderState) {
        ProductOrder productOrder = orderRepository.findById(orderId).orElseThrow(
                OrderNotFoundException::new);
        productOrder.UpdateOrderState(orderState);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        ProductOrder productOrder = orderRepository.findById(orderId).orElseThrow(
                OrderNotFoundException::new);
        orderRepository.deleteById(orderId);
    }

    private ProductOrder isOrderExist(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                OrderNotFoundException::new);
    }

}
