package com.shopping.application.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "Products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_ID")
    private int productId;

    @Column(name = "Product_Name")
    private String productName;

    @Column(name = "Price")
    private int price;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Description")
    private String description;

    @Column(name = "Discount")
    private String discount;

    @Column(name = "Published")
    private boolean isPublished;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private ProductCategory productCategory;


}
