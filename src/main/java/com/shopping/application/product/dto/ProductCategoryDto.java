package com.shopping.application.product.dto;

import com.shopping.application.product.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductCategoryDto {
    private int productCategoryId;
    private String name;
    private boolean isPublished;
    private List<Products> products;
}
