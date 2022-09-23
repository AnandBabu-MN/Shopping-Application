package com.shopping.application.order.controller;

import com.shopping.application.order.dto.OrderDto;
import com.shopping.application.order.entity.Order;
import com.shopping.application.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto) {
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/findAllOrders")
    public List<Order> findAllOrders() {
        return orderService.findAllOrders();
    }

    @DeleteMapping("/delete")
    public String deleteOrder(@RequestParam int orderId){
        return  orderService.deleteOrder(orderId);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto){
        return orderService.updateOrder(orderDto);
    }

    @PutMapping("/cancel")
    String cancelOrder(@RequestParam int orderId){
        return orderService.cancelOrder(orderId);
    }

}
