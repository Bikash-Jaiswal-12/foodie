package com.foodie.restaurant_service.dto;

import lombok.Data;

@Data
public class MenuResponseDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String category;

}
