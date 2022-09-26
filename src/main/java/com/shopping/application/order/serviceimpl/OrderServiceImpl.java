package com.shopping.application.order.serviceimpl;

import com.shopping.application.common.entity.UserProduct;
import com.shopping.application.common.repository.UserProductRepository;
import com.shopping.application.config.ErrorCodes;
import com.shopping.application.config.ResponseMessage;
import com.shopping.application.order.dto.OrderDto;
import com.shopping.application.order.entity.Order;
import com.shopping.application.order.repository.OrderDetailsRepository;
import com.shopping.application.order.service.OrderService;
import com.shopping.application.product.entity.Products;
import com.shopping.application.product.repository.ProductRepository;
import com.shopping.application.user.dto.UserDto;
import com.shopping.application.user.entity.User;
import com.shopping.application.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserProductRepository userProductRepository;

    @Override
    public List<Order> findAllOrders() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public ResponseEntity<OrderDto> placeOrder(OrderDto orderDto) {
        orderDto.setUser(modelMapper.map(getLoggerInUser(), UserDto.class));
        try {
            if (!validateOrder(orderDto).get()) throw new IllegalArgumentException(ErrorCodes.ORDER_QUANTITY_INVALID);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(new ResponseMessage(true, "Quantity Exceeded the Current Limit!"), HttpStatus.OK);
        }
        List<UserProduct> userProductList = orderDto.getUserProducts().stream().map(userProductDto -> {
            UserProduct userProduct = modelMapper.map(userProductDto, UserProduct.class);
            userProduct.setUsers(modelMapper.map(orderDto.getUser(), User.class));
            userProduct.setProducts(productRepository.findById(userProduct.getProducts().getProductId()).orElseThrow(() ->
                    new IllegalArgumentException(ErrorCodes.PRODUCT_NOT_FOUND)));
            return userProductRepository.save(userProduct);
        }).collect(Collectors.toList());

        Order order = modelMapper.map(orderDto, Order.class);
        order.setUserProducts(userProductList);
        Order savedOrder = orderDetailsRepository.save(order);
        AtomicInteger totalPrice = new AtomicInteger();
        savedOrder.getUserProducts().stream().forEach(userProduct -> {
            validateProduct(userProduct);
            Products products = userProduct.getProducts();
            products.setQuantity(products.getQuantity() - userProduct.getQuantity());
            productRepository.save(products);
            totalPrice.addAndGet(products.getPrice());
        });
        savedOrder.setTotalPrice(totalPrice.get());
        savedOrder.setOrderStatus("CONFIRMED");
        savedOrder.setOrderedTime(LocalDateTime.now());
        Order savedOrder2 = orderDetailsRepository.save(savedOrder);
        OrderDto ordersDto = modelMapper.map(savedOrder, OrderDto.class);
        return new ResponseEntity(ordersDto, HttpStatus.OK);
    }

    @Override
    public String deleteOrder(int orderId) {
        orderDetailsRepository.deleteById(orderId);
        return "Order Deleted Successfully";
    }

    @Override
    public ResponseEntity<OrderDto> updateOrder(OrderDto orderDto) {
        orderDto.setOrderStatus("MODIFIED");
        return placeOrder(orderDto);
    }

    @Override
    public String cancelOrder(int orderId) {
        Order order = orderDetailsRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.ORDER_NOT_FOUND));
        order.setOrderStatus("Cancelled");
        orderDetailsRepository.save(order);
        return "Order Cancelled";
    }


    private AtomicBoolean validateOrder(OrderDto orderDto) {
        AtomicBoolean isAvailable = new AtomicBoolean(true);
        orderDto.getUserProducts().stream().forEach(userProduct -> {
            if (productRepository.findById(userProduct.getProducts().getProductId()).orElseThrow(() ->
                    new IllegalArgumentException(ErrorCodes.PRODUCT_NOT_FOUND)).getQuantity()
                    <= userProduct.getQuantity()) isAvailable.set(false);
        });
        return isAvailable;
    }

    private void validateProduct(UserProduct userProduct) {
        Products products = userProduct.getProducts();
        Products products1 = productRepository.findById(products.getProductId()).orElseThrow(() ->
                new IllegalArgumentException(ErrorCodes.PRODUCT_NOT_FOUND));
        if (products1.getQuantity()
                == products.getQuantity()) products1.setPublished(false);
        productRepository.save(products1);
    }

    @Override
    public User getLoggerInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User loggerInUser = userRepository.findByEmail(email).get();
        return loggerInUser;
    }
}
