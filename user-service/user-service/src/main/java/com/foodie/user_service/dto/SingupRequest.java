package com.foodie.user_service.dto;

import com.foodie.user_service.entity.RoleEnum;
import lombok.Data;

@Data
public class SingupRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private RoleEnum role;

}
