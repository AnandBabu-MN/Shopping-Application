package com.shopping.application.product.controller;

import com.shopping.application.config.ResponseMessage;
import com.shopping.application.product.dto.ProductDto;
import com.shopping.application.product.entity.Products;
import com.shopping.application.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getAllProducts")
    List<Products> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById")
    Optional<Products> getProductById(@RequestParam int id){
        return productService.getProductById(id);
    }

    @PostMapping("/insertProduct")
    ResponseEntity<ProductDto> insertNewProduct(@RequestBody ProductDto productDto){
        return productService.insertNewProduct(productDto);
    }

    @PutMapping("/updateProduct")
    ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@RequestParam int id){
        return productService.updateProduct(productDto,id);
    }

    @DeleteMapping("/deleteProduct")
    ResponseEntity<ResponseMessage> deleteProductById(@RequestParam int id){
        return productService.deleteProductById(id);
    }

}
