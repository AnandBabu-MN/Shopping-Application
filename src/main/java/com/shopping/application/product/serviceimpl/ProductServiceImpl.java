package com.shopping.application.product.serviceimpl;

import com.shopping.application.config.ErrorCodes;
import com.shopping.application.config.ResponseMessage;
import com.shopping.application.product.dto.ProductDto;
import com.shopping.application.product.entity.Products;
import com.shopping.application.product.repository.ProductRepository;
import com.shopping.application.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Products> getAllProductsCustomer() {
        return getAllProducts().stream().filter(products -> products.isPublished() && products.getProductCategory().isPublished()).collect(Collectors.toList());
    }

    @Override
    public Products getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorCodes.PRODUCT_NOT_FOUND));
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(ProductDto productDto, int id) {
        productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.PRODUCT_NOT_FOUND));
        Products updateProduct = modelMapper.map(productDto, Products.class);
        Products updatedProduct = productRepository.save(updateProduct);
        modelMapper.map(updatedProduct, ProductDto.class);
        return new ResponseEntity(new ResponseMessage(true, "Product Details Updated"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteProductById(int id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorCodes.PRODUCT_NOT_FOUND);
        }
        return new ResponseEntity(new ResponseMessage(true, "Product has been deleted"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> insertNewProduct(ProductDto productDto) {
        if (productRepository.findById(productDto.getProductId()).isPresent()) {
            throw new IllegalArgumentException(ErrorCodes.EXISTS_PRODUCT);
        }
        Products products = modelMapper.map(productDto, Products.class);
        Products newProduct = productRepository.save(products);
        modelMapper.map(newProduct, ProductDto.class);
        return new ResponseEntity(new ResponseMessage(true, "New Product Inserted to Inventory"), HttpStatus.OK);
    }
}
