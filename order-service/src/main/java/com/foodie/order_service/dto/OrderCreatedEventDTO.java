package com.foodie.order_service.dto;

import lombok.Data;

@Data
public class OrderCreatedEventDTO {
    private Long orderId;
    private Long userId;
    private Long restaurantId;
    private Long itemId;
    private Double amount;
}
