package com.example.order.domain.order.repository;

import com.example.order.domain.order.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ProductOrder,Long> {

}
