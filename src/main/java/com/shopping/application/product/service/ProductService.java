package com.shopping.application.product.service;

import com.shopping.application.config.ResponseMessage;
import com.shopping.application.product.dto.ProductDto;
import com.shopping.application.product.entity.Products;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Products> getAllProducts();

    List<Products> getAllProductsCustomer();

    Products getProductById(int id);
    ResponseEntity<ProductDto> updateProduct(ProductDto productDto, int id);
    ResponseEntity<ResponseMessage> deleteProductById(int id);
    ResponseEntity<ProductDto> insertNewProduct(ProductDto productDto);



}
