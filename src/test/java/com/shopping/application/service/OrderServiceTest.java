package com.shopping.application.service;

import com.shopping.application.common.dto.UserProductDto;
import com.shopping.application.common.entity.UserProduct;
import com.shopping.application.order.dto.OrderDto;
import com.shopping.application.order.entity.Order;
import com.shopping.application.order.repository.OrderDetailsRepository;
import com.shopping.application.order.serviceimpl.OrderServiceImpl;
import com.shopping.application.user.dto.UserDto;
import com.shopping.application.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    @Mock
    OrderDetailsRepository orderDetailsRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    Authentication auth;

    OrderDto orderDto = new OrderDto();

    Order order = new Order();

    List<Order> orderList = new ArrayList<>();

    User user = new User();

    @BeforeEach
    void setUp() {
        order.setOrderId(1);
        order.setTotalPrice(100.00);
        order.setOrderStatus("Order Confirmed");
        order.setOrderedTime(LocalDateTime.now());
        order.setUser(new User());
        order.setUserProducts(new ArrayList<UserProduct>());
        orderList.add(order);
        //OrderDto
        orderDto.setOrderId(1);
        orderDto.setTotalPrice(100.00);
        orderDto.setOrderStatus("Order Confirmed");
        orderDto.setOrderedTime(LocalDateTime.now());
        orderDto.setUser(new UserDto());
        orderDto.setUserProducts(new ArrayList<UserProductDto>());
        //User
        user.setUserId(1L);
        user.setFirstName("Anand");
        user.setLastName("Babu");
        user.setAddress("Madurai");
        user.setDob("27th May 2001");
        user.setEmail("anand@gmail.com");
        user.setGender("Male");
        user.setPassword("Anand@1234");
        user.setPhoneNumber("1234567890");
        user.setAdmin(true);
    }

    @Test
    void findAllOrders() {
        when(orderDetailsRepository.findAll()).thenReturn(orderList);
        assertThat(orderService.findAllOrders()).isEqualTo(orderList);
    }

    @Test
    void placeOrder() {
        Mockito.when(orderService.getLoggerInUser().getEmail()).thenReturn(String.valueOf(user));
        Mockito.when(orderDetailsRepository.save(order)).thenReturn(order);
        assertThat(orderService.placeOrder(orderDto).getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void cancelOrder() {
        when(orderDetailsRepository.findById(1)).thenReturn(Optional.ofNullable(order));
        when(orderDetailsRepository.save(order)).thenReturn(order);
        assertThat(orderService.cancelOrder(1)).isEqualTo("Order Cancelled");
    }

    @Test
    void deleteOrder() {
        orderService.deleteOrder(1);
        verify(orderDetailsRepository, Mockito.times(1)).deleteById(any());
    }
}
