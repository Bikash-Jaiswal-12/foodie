package com.foodie.restaurant_service.controller;

import com.foodie.restaurant_service.dto.MenuRequestDto;
import com.foodie.restaurant_service.dto.MenuResponseDto;
import com.foodie.restaurant_service.dto.RestaurantRequestDto;
import com.foodie.restaurant_service.dto.RestaurantResponseDto;
import com.foodie.restaurant_service.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/restaurnts")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody RestaurantRequestDto dto){

        return ResponseEntity.ok(
                restaurantService.createRestaurant(dto));
    }

    @PostMapping("/{id}/menu")
    public ResponseEntity<?> addMenu(
            @PathVariable Long id,
            @RequestBody MenuRequestDto dto){

        return ResponseEntity.ok(
                restaurantService.addMenu(id,dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurant(
            @PathVariable Long id){

        return ResponseEntity.ok(
                restaurantService.getRestaurant(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {

        List<RestaurantResponseDto> restaurants =
                restaurantService.getAllRestaurants();

        return ResponseEntity.ok(restaurants);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<?> uploadImage(
            @PathVariable Long id,
            @RequestParam MultipartFile file)
            throws IOException {

        restaurantService.uploadRestaurantImage(id,file);
        return ResponseEntity.ok("uploaded");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(
            @PathVariable Long id){

        restaurantService.deleteRestaurant(id);

        return ResponseEntity.ok(
                "Restaurant and its menu deleted successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> updateRestaurant(
            @PathVariable Long id,
            @RequestBody RestaurantRequestDto dto){

        RestaurantResponseDto updated =
                restaurantService.updateRestaurant(id, dto);

        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/menu/delete/{id}")
    public ResponseEntity<String> deleteMenu(
            @PathVariable Long id){

        restaurantService.deleteMenuItem(id);

        return ResponseEntity.ok(
                "Menu deleted successfully");
    }

    @PatchMapping("/menu/update/{id}")
    public ResponseEntity<MenuResponseDto> updateMenuItem(
            @PathVariable Long id,
            @RequestBody MenuRequestDto dto){

        MenuResponseDto updated =
                restaurantService.updateMenu(id, dto);

        return ResponseEntity.ok(updated);
    }

}
