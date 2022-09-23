package com.shopping.application.common.entity;


import com.shopping.application.order.entity.Order;
import com.shopping.application.product.entity.Products;
import com.shopping.application.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_product")
public class UserProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    private User users;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    private Products products;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;
}
