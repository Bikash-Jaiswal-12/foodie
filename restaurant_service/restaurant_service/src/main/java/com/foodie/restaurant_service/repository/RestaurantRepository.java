package com.foodie.restaurant_service.repository;

import com.foodie.restaurant_service.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {

}
