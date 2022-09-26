package com.shopping.application.product.dto;

import com.shopping.application.product.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {
    private int productId;
    private String productName;
    private int price;
    private int quantity;
    private String description;
    private String discount;
    private boolean isPublished;
    private ProductCategory productCategory;
}
