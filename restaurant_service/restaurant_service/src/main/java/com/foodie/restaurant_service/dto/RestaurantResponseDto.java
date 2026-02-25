package com.foodie.restaurant_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String city;
    private String openingTime;
    private String closingTime;
    private List<MenuResponseDto> menu;
}
