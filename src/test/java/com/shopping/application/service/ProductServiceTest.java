package com.shopping.application.service;

import com.shopping.application.product.dto.ProductCategoryDto;
import com.shopping.application.product.dto.ProductDto;
import com.shopping.application.product.entity.ProductCategory;
import com.shopping.application.product.entity.Products;
import com.shopping.application.product.repository.ProductRepository;
import com.shopping.application.product.serviceimpl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ModelMapper modelMapper;

    Products products = new Products();

    ProductCategory productCategory = new ProductCategory();

    List<Products> productsList = new ArrayList<>();

    ProductDto productDto = new ProductDto();

    ProductCategoryDto productCategoryDto = new ProductCategoryDto();

    @BeforeEach
    void setUp() {
        //Products
        products.setProductId(1);
        products.setProductName("Hamam");
        products.setPrice(50);
        products.setQuantity(10);
        products.setDescription("Protection for Your skin");
        products.setDiscount("5%");
        products.setPublished(true);
        products.setProductCategory(productCategory);
        productsList.add(products);
        //Product Category
        productCategory.setProductCategoryId(1);
        productCategory.setName("Soap");
        productCategory.setPublished(true);
        //Product DTO
        productDto.setProductId(1);
        productDto.setProductName("Hamam");
        productDto.setPrice(50);
        productDto.setQuantity(10);
        productDto.setDescription("Protection for Your skin");
        productDto.setDiscount("5%");
        productDto.setPublished(true);
        productDto.setProductCategory(productCategory);
    }

    @Test
    void getAllProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(productsList);
        assertThat(productService.getAllProducts()).isEqualTo(productsList);
    }

    @Test
    void getAllProductsCustomer() {
        Mockito.when(productRepository.findAll()).thenReturn(productsList);
        assertThat(productService.getAllProductsCustomer()).isEqualTo(productsList);
    }

    @Test
    void getProductById() {
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.ofNullable(products));
        assertThat(productService.getProductById(1)).isEqualTo(products);
    }

    @Test
    void insertNewProduct() {
        Mockito.when(productRepository.save(products)).thenReturn(products);
        assertThat(productService.insertNewProduct(productDto).getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void updateProduct(){
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.ofNullable(products));
        Mockito.when(productRepository.save(products)).thenReturn(products);
        assertThat(productService.updateProduct(productDto,1).getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    void deleteProductById(){
        productService.deleteProductById(1);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(any());
    }


}
