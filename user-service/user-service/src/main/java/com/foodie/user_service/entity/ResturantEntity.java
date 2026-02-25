package com.foodie.user_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "foodie_resturant")
public class ResturantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resturant_id;
    private String name;
    private String description;
    private String delivery_time;
    private String opening_time;
    private String closing_time;
    private String address;
    private double rating;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;
    private boolean isFavourite;
}
