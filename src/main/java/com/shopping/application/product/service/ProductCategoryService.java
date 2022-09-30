package com.shopping.application.product.service;

import com.shopping.application.config.ResponseMessage;
import com.shopping.application.product.dto.ProductCategoryDto;
import com.shopping.application.product.entity.ProductCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductCategoryService {
    List<ProductCategory> getAllProductCategory();

    List<ProductCategory> getAllProductCategoryCustomer();

    ProductCategory getProductCategoryById(int id);
    ResponseEntity<ProductCategoryDto> updateProductCategory(ProductCategoryDto productCategoryDto, int id);
    ResponseEntity<ResponseMessage> deleteProductCategoryById(int id);
    ResponseEntity<ProductCategoryDto> insertNewProductCategory(ProductCategoryDto productCategoryDto);
}
