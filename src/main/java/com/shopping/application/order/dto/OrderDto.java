package com.shopping.application.order.dto;

import com.shopping.application.common.dto.UserProductDto;
import com.shopping.application.common.entity.UserProduct;
import com.shopping.application.product.entity.Products;
import com.shopping.application.user.dto.UserDto;
import com.shopping.application.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
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
}
