package com.shopping.application.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int productCategoryId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Published")
    private boolean isPublished;

    @OneToMany(targetEntity = Products.class,fetch = FetchType.LAZY,mappedBy = "productCategory")
    private List<Products> products;

}
