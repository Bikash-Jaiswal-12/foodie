package com.foodie.user_service.repository;

import com.foodie.user_service.entity.ResturantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResturantRepository extends JpaRepository<ResturantEntity, Long> {

}
