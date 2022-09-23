package com.shopping.application.order.entity;

import com.shopping.application.common.entity.UserProduct;
import com.shopping.application.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int orderId;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "OrderStatus")
    private String orderStatus;

    @Column(name = "Ordered_Time")
    private LocalDateTime orderedTime;

    @OneToMany(targetEntity = UserProduct.class,fetch = FetchType.LAZY,mappedBy = "order")
    private List<UserProduct> userProducts;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private User user;


}
