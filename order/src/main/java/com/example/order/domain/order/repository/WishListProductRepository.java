package com.example.order.domain.order.repository;

import com.example.order.domain.order.entity.WishListProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListProductRepository extends JpaRepository<WishListProduct,Long> {
    WishListProduct findByProductIdAndWishListId(Long productId, Long wishListId);
    List<WishListProduct> findByWishListId(Long wishListId);
}
