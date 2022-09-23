package com.shopping.application.common.repository;

import com.shopping.application.common.entity.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct,Integer> {
}
