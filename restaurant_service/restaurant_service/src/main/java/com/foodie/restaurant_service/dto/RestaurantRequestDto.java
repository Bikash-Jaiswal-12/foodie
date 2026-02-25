package com.foodie.restaurant_service.dto;

import lombok.Data;

@Data
public class RestaurantRequestDto {
    private String name;
    private String city;
    private String openingTime;
    private String closingTime;


}
