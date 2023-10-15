package com.restaura.server.dto;

import com.restaura.server.entity.Dish;
import com.restaura.server.util.enums.OrderStatus;
import com.restaura.server.util.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long customerId;
    private Long restaurantId;
    private Timestamp orderTime;
    private int estimatedDeliveryTime;
    private OrderStatus orderStatus;
    private double totalAmount;
    private PaymentStatus paymentStatus;
    private String remarks;
    private Set<Dish> dishes = new HashSet<>();
}
