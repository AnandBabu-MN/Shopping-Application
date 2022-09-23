package com.shopping.application.product.repository;

import com.shopping.application.product.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
//    @Query("SELECT quantity FROM products")
//    int getQuantity(int quantity);
}
