package com.foodie.order_service.service;

import com.foodie.order_service.entity.OrderEntity;

public interface OrderService {
    public OrderEntity placeOrder(String token,
                                  Long restaurantId,
                                  Long menuItemId);
    public OrderEntity getOrder(Long orderId);

}
