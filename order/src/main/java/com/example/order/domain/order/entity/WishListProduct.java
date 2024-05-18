package com.example.order.domain.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "wishproduct")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WishListProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long quantities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id", nullable = false)
    private WishList wishList;

    @Builder
    public WishListProduct(Long productId, Long quantities, WishList wishList) {
        this.productId = productId;
        this.quantities = quantities;
        this.wishList = wishList;
    }

    public void updateQuantities(Long newQuantities){
        this.quantities=newQuantities;
    }
}
