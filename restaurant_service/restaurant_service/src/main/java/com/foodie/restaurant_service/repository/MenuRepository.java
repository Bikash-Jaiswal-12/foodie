package com.foodie.restaurant_service.repository;

import com.foodie.restaurant_service.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity,Long> {
}
