package com.restaura.server.entity;

import com.restaura.server.util.enums.OrderStatus;
import com.restaura.server.util.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Table(name = "orders")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    @Column(name = "RESTAURANT_ID")
    private Long restaurantId;
    @Column(name = "ORDER_TIME")
    private Timestamp orderTime;
    @Column(name = "ESTIMATED_DELIVERY_TIME")
    private int estimatedDeliveryTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private OrderStatus orderStatus;
    @Column(name = "TOTAL_AMOUNT")
    private double totalAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus paymentStatus;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;
    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "UPDATED_BY")
    private Long updatedBy;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "order__dishes",
            joinColumns = { @JoinColumn(name = "ORDER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "DISH_ID") })
    private Set<Dish> dishes = new HashSet<>();

}
