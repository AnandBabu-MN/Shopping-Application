package com.shopping.application.product.serviceimpl;

import com.shopping.application.config.ErrorCodes;
import com.shopping.application.config.ResponseMessage;
import com.shopping.application.product.dto.ProductCategoryDto;
import com.shopping.application.product.entity.ProductCategory;
import com.shopping.application.product.repository.ProductCategoryRepository;
import com.shopping.application.product.service.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProductCategory> getAllProductCategory() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> getAllProductCategoryCustomer() {
        return getAllProductCategory().stream().filter(productCategory -> productCategory.isPublished()).collect(Collectors.toList());
    }

    @Override
    public ProductCategory getProductCategoryById(int id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorCodes.PRODUCT_NOT_FOUND));

    }

    @Override
    public ResponseEntity<ProductCategoryDto> updateProductCategory(ProductCategoryDto productCategoryDto, int id) {
        productCategoryRepository.findById(id).orElseThrow(()->new IllegalArgumentException(ErrorCodes.PRODUCT_NOT_FOUND));
        ProductCategory updateProductCategory = modelMapper.map(productCategoryDto,ProductCategory.class);
        ProductCategory updatedProductCategory = productCategoryRepository.save(updateProductCategory);
        modelMapper.map(updatedProductCategory, ProductCategoryDto.class);
        return new ResponseEntity(new ResponseMessage(true,"ProductCategory Details Updated"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteProductCategoryById(int id) {
        try {
            productCategoryRepository.findById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorCodes.PRODUCT_NOT_FOUND);
        }
        return new ResponseEntity(new ResponseMessage(true,"ProductCategory has been deleted"),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductCategoryDto> insertNewProductCategory(ProductCategoryDto productCategoryDto) {
        if(productCategoryRepository.findById(productCategoryDto.getProductCategoryId()).isPresent()){
            throw new IllegalArgumentException(ErrorCodes.EXISTS_PRODUCT);
        }
        ProductCategory productCategory = modelMapper.map(productCategoryDto,ProductCategory.class);
        ProductCategory newProductCategory = productCategoryRepository.save(productCategory);
        modelMapper.map(newProductCategory,ProductCategoryDto.class);
        return new ResponseEntity(new ResponseMessage(true,"New Product category Inserted to Inventory"),HttpStatus.OK);
    }
}
