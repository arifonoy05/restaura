package com.restaura.server.dto;

import com.restaura.server.entity.FoodCategory;
import com.restaura.server.entity.Order;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {
    @Nullable
    private Long id;
    private String name;
    private String description;
    private double price;
    @Nullable
    private String imageUrl;
    private boolean isAvailable;

    private Long foodCategoryId;



}
