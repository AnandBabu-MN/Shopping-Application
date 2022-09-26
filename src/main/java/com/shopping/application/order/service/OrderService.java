package com.shopping.application.order.service;

import com.shopping.application.order.dto.OrderDto;
import com.shopping.application.order.entity.Order;
import com.shopping.application.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
     List<Order> findAllOrders();

     ResponseEntity<OrderDto> placeOrder(OrderDto orderDto);

     String deleteOrder(int orderId);
     ResponseEntity<OrderDto> updateOrder(OrderDto orderDto);
     String cancelOrder(int orderId);


    User getLoggerInUser();
}
