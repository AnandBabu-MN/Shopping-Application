package com.shopping.application.common.dto;

import com.shopping.application.product.entity.Products;
import com.shopping.application.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@Setter
public class UserProductDto {

    private Products products;

    private int quantity;
}
