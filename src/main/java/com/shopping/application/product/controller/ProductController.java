package com.shopping.application.product.controller;

import com.shopping.application.config.ResponseMessage;
import com.shopping.application.product.dto.ProductDto;
import com.shopping.application.product.entity.Products;
import com.shopping.application.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getAllProducts")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    List<Products> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/getAllProductsCustomer")
    List<Products> getAllProductsCustomer(){
        return productService.getAllProductsCustomer();
    }

    @GetMapping("/getProductById")
    Optional<Products> getProductById(@RequestParam int id){
        return productService.getProductById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/insertProduct")
    ResponseEntity<ProductDto> insertNewProduct(@RequestBody ProductDto productDto){
        return productService.insertNewProduct(productDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/updateProduct")
    ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@RequestParam int id){
        return productService.updateProduct(productDto,id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteProduct")
    ResponseEntity<ResponseMessage> deleteProductById(@RequestParam int id){
        return productService.deleteProductById(id);
    }

}
