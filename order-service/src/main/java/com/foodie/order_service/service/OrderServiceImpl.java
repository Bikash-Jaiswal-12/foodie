package com.foodie.order_service.service;

import com.foodie.order_service.config.RestTemplateConfig;
import com.foodie.order_service.dto.MenuDto;
import com.foodie.order_service.dto.OrderCreatedEventDTO;
import com.foodie.order_service.dto.RestaurantDto;
import com.foodie.order_service.entity.OrderEntity;
import com.foodie.order_service.entity.OrderStatus;
import com.foodie.order_service.kafka.OrderProducer;
import com.foodie.order_service.repository.OrderRepository;
import com.foodie.order_service.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger =
            LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private RestTemplate restTemplate;

    public OrderEntity placeOrder(String token,
                                  Long restaurantId,
                                  Long menuItemId) {

        logger.info("Received order request for restaurantId={} and menuItemId={}",
                restaurantId, menuItemId);

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        Long userId = jwtUtil.extractUserId(token);
        logger.info("User extracted from token: userId={}", userId);

        String restUrl = "http://localhost:8082/api/restaurants/" + restaurantId;
        RestaurantDto restaurant =
                restTemplate.getForObject(restUrl, RestaurantDto.class);

        System.out.println(restaurant);

        if (restaurant == null) {
            logger.error("Restaurant not found with id={}", restaurantId);
            throw new RuntimeException("Restaurant not found");
        }

        logger.info("Restaurant found: {}", restaurant.getName());

        String menuUrl = "http://localhost:8082/api/restaurants/menu/" + menuItemId;
        MenuDto menu =
                restTemplate.getForObject(menuUrl, MenuDto.class);

        if (menu == null) {
            logger.error("Menu item not found with id={}", menuItemId);
            throw new RuntimeException("Menu not found");
        }

        logger.info("Menu item found: {} with price={}",
                menu.getName(), menu.getPrice());

        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setRestaurantId(restaurantId);
        order.setItemId(menuItemId);
        order.setAmount(menu.getPrice()); // better
        order.setStatus(OrderStatus.PAYMENT_PENDING);
        order.setCreatedAt(LocalDateTime.now());

        OrderEntity savedOrder = orderRepository.save(order);
        logger.info("Order saved successfully with orderId={}",
                savedOrder.getId());

        OrderCreatedEventDTO createdto = new OrderCreatedEventDTO();
        createdto.setUserId(userId);
        createdto.setOrderId(order.getId());
        createdto.setRestaurantId(restaurantId);
        createdto.setItemId(menuItemId);
        createdto.setAmount(menu.getPrice());

        orderProducer.sendOrderCreatedEvent(createdto);
        logger.info("OrderCreatedEvent sent to Kafka for orderId={}",
                savedOrder.getId());

        return savedOrder;
    }


    public OrderEntity getOrder(Long orderId) {
        logger.info("Fetching order with id={}", orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    logger.error("Order not found with id={}", orderId);
                    return new RuntimeException("Order not found");
                });
    }
}
