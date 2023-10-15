package com.restaura.server.dto;

import com.restaura.server.entity.Restaurant;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCategoryDTO {
    @Nullable
    private Long id;
    private String description;
    private String name;
    private Restaurant restaurant;
}
