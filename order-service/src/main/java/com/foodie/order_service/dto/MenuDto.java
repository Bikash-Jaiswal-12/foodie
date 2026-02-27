package com.foodie.order_service.dto;

import lombok.Data;

@Data
public class MenuDto {
    private Long id;
    private String name;
    private Double price;
    private Long restaurantId;
}
