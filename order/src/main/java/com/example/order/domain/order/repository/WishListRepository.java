package com.example.order.domain.order.repository;

import com.example.order.domain.order.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Long> {
    boolean existsByUserId(Long userId);
    WishList findByUserId(Long userId);
}
