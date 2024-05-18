package com.example.order.domain.order.repository;

import com.example.order.domain.order.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ProductOrder,Long> {

}
