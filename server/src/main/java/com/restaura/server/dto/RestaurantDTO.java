package com.restaura.server.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class RestaurantDTO {
    @Nullable
    private Long id;
    private String name;
    private String foundingYear;
    private String email;
    private String websiteUrl;
    private String phone;
    private String openingHours;
    private int capacity;
    private float rating;
    private String address;
    private String city;
    private String postalCode;
    private boolean open;
}
