package com.foodie.order_service.controller;

import com.foodie.order_service.entity.OrderEntity;
import com.foodie.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create/{restaurantId}/{menuItemId}")
    public ResponseEntity<OrderEntity> placeOrder(
            @RequestHeader("Authorization") String token,
            @PathVariable Long restaurantId,
            @PathVariable Long menuItemId)
            {

        OrderEntity order = orderService.placeOrder(token, restaurantId, menuItemId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/admin/{orderId}")
    public ResponseEntity<OrderEntity> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }
}
