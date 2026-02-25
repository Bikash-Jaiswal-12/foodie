package com.foodie.user_service.service;

import com.foodie.user_service.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity createUser(UserEntity userEntity);
    Optional<UserEntity> getUserById(Long id);
    List<UserEntity> getAllUser();
 //   UserEntity findUserProfileByJwt(String jwt);

}