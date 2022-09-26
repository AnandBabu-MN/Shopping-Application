package com.shopping.application.common.dto;

import com.shopping.application.product.entity.Products;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProductDto {

    private Products products;

    private int quantity;
}
