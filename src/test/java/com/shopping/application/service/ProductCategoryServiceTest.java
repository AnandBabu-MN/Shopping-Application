package com.shopping.application.service;

import com.shopping.application.product.dto.ProductCategoryDto;
import com.shopping.application.product.entity.ProductCategory;
import com.shopping.application.product.entity.Products;
import com.shopping.application.product.repository.ProductCategoryRepository;
import com.shopping.application.product.serviceimpl.ProductCategoryServiceImpl;
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
public class ProductCategoryServiceTest {


    @Mock
    ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    ProductCategoryServiceImpl productCategoryService;

    @Mock
    ModelMapper modelMapper;

    ProductCategory productCategory = new ProductCategory();

    List<ProductCategory> productCategories = new ArrayList<>();

    ProductCategoryDto productCategoryDto = new ProductCategoryDto();

    List<Products> productsList = new ArrayList<>();

    Products products=new Products();


    @BeforeEach
    void setUp(){
        productCategory.setProductCategoryId(1);
        productCategory.setName("Soap");
        productCategory.setPublished(true);
        productCategory.setProducts(productsList);
        productCategories.add(productCategory);
        //Products Details
        products.setProductId(1);
        products.setProductName("Hamam");
        products.setPrice(50);
        products.setQuantity(10);
        products.setDescription("Protection for Your skin");
        products.setDiscount("5%");
        products.setPublished(true);
        productsList.add(products);
        //Product Category Dto
        productCategoryDto.setProductCategoryId(1);
        productCategoryDto.setName("Soap");
        productCategoryDto.setPublished(true);
        productCategoryDto.setProducts(productsList);
    }

    @Test
    void getAllProductsCategory() {
        Mockito.when(productCategoryRepository.findAll()).thenReturn(productCategories);
        assertThat(productCategoryService.getAllProductCategory()).isEqualTo(productCategories);

    }

    @Test
    void getAllProductCategoryCustomer() {
        Mockito.when(productCategoryRepository.findAll()).thenReturn(productCategories);
        assertThat(productCategoryService.getAllProductCategoryCustomer()).isEqualTo(productCategories);
    }

    @Test
    void getProductCategoryById() {
        Mockito.when(productCategoryRepository.findById(1)).thenReturn(Optional.ofNullable(productCategory));
        assertThat(productCategoryService.getProductCategoryById(1)).isEqualTo(productCategory);
    }

    @Test
    void insertNewProductCategory() {
        Mockito.when(productCategoryRepository.save(productCategory)).thenReturn(productCategory);
        assertThat(productCategoryService.insertNewProductCategory(productCategoryDto).getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void updateProductCategory(){
        Mockito.when(productCategoryRepository.findById(any())).thenReturn(Optional.ofNullable(productCategory));
        Mockito.when(productCategoryRepository.save(productCategory)).thenReturn(productCategory);
        assertThat(productCategoryService.updateProductCategory(productCategoryDto,1).getStatusCodeValue()).isEqualTo(200);
    }
}
