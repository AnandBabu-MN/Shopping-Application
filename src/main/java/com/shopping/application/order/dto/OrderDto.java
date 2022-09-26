package com.shopping.application.order.dto;

import com.shopping.application.common.dto.UserProductDto;
import com.shopping.application.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
        private int orderId;
        private double totalPrice;
        private LocalDateTime orderedTime;
        private List<UserProductDto> userProducts;
        private UserDto user;
        private String orderStatus;
}
