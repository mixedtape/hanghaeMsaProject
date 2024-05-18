package com.example.order.domain.order.entity;

import com.example.order.global.auditing.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "productOrder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOrder extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long totalPrice;

    @Column(nullable = false)
    private String  orderNumber;

    @Column(nullable = false)
    private LocalDateTime orderTime;

    @Column(nullable = false)
    private LocalDateTime deliveryTime;

    @Column(nullable = false)
    private String orderStat;

    @Column(nullable = false)
    private String address;

    @Builder
    public ProductOrder(Long totalPrice, LocalDateTime orderTime,
            LocalDateTime deliveryTime, String orderStat, String address) {
        this.totalPrice = totalPrice;
        this.orderNumber = UUID.randomUUID().toString(); // UUID를 생성하여 할당
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
        this.orderStat = orderStat;
        this.address = address;
    }
    public void UpdateOrderState(String orderStat)
    {
        this.orderStat = orderStat;
    }


}
