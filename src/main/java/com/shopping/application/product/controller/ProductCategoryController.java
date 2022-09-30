package com.shopping.application.product.controller;

import com.shopping.application.config.ResponseMessage;
import com.shopping.application.product.dto.ProductCategoryDto;
import com.shopping.application.product.entity.ProductCategory;
import com.shopping.application.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getAllProductCategory")
    List<ProductCategory> getAllProductCategory(){
        return productCategoryService.getAllProductCategory();
    }

    @GetMapping("/getAllProductCategoryCustomer")
    List<ProductCategory> getAllProductCategoryCustomer(){
        return productCategoryService.getAllProductCategoryCustomer();
    }

    @GetMapping("/getProductCategoryById")
    public ProductCategory getProductCategoryById(@RequestParam int id){
        return productCategoryService.getProductCategoryById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/insertNewCategory")
    public ResponseEntity<ProductCategoryDto> insertNewProductCategory(@RequestBody ProductCategoryDto productCategoryDto){
        return productCategoryService.insertNewProductCategory(productCategoryDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/updateCategory")
    public ResponseEntity<ProductCategoryDto> updateProductCategory(@RequestBody ProductCategoryDto productCategoryDto,@RequestParam int id){
        return productCategoryService.updateProductCategory(productCategoryDto,id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteCategory")
    public ResponseEntity<ResponseMessage> deleteProductCategoryById(@RequestParam int id){
        return productCategoryService.deleteProductCategoryById(id);
    }
}
