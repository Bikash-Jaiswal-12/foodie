package com.foodie.restaurant_service.service;

import com.foodie.restaurant_service.dto.MenuRequestDto;
import com.foodie.restaurant_service.dto.MenuResponseDto;
import com.foodie.restaurant_service.dto.RestaurantRequestDto;
import com.foodie.restaurant_service.dto.RestaurantResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RestaurantService {
    RestaurantResponseDto createRestaurant(
            RestaurantRequestDto dto);

    MenuResponseDto addMenu(
            Long restaurantId,
            MenuRequestDto dto);

    RestaurantResponseDto getRestaurant(Long id);

    void uploadRestaurantImage(
            Long id,
            MultipartFile file) throws IOException;
    public List<RestaurantResponseDto> getAllRestaurants();

    public void deleteRestaurant(Long id);
    public RestaurantResponseDto updateRestaurant(
            Long id,
            RestaurantRequestDto dto);
    public void deleteMenuItem(Long id);
    public MenuResponseDto updateMenu(
            Long id,
            MenuRequestDto dto);
}
