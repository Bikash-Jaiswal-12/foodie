package com.foodie.user_service.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "foodie_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String city;
    private String state;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = true)
    private Long mobile;

}
