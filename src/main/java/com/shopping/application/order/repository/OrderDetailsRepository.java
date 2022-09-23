package com.shopping.application.order.repository;

import com.shopping.application.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<Order,Integer> {
}
