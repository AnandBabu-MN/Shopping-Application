package com.shopping.application.product.controller;

import com.shopping.application.config.ResponseMessage;
import com.shopping.application.product.dto.ProductCategoryDto;
import com.shopping.application.product.entity.ProductCategory;
import com.shopping.application.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/getAllProductCategory")
    List<ProductCategory> getAllProductCategory(){
        return productCategoryService.getAllProductCategory();
    }

    @GetMapping("/getProductCategoryById")
    public Optional<ProductCategory> getProductCategoryById(@RequestParam int id){
        return productCategoryService.getProductCategoryById(id);
    }

    @PostMapping("/insertNewCategory")
    public ResponseEntity<ProductCategoryDto> insertNewProductCategory(@RequestBody ProductCategoryDto productCategoryDto){
        return productCategoryService.insertNewProductCategory(productCategoryDto);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<ProductCategoryDto> updateProductCategory(@RequestBody ProductCategoryDto productCategoryDto,@RequestParam int id){
        return productCategoryService.updateProductCategory(productCategoryDto,id);
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<ResponseMessage> deleteProductCategoryById(@RequestParam int id){
        return productCategoryService.deleteProductCategoryById(id);
    }
}
