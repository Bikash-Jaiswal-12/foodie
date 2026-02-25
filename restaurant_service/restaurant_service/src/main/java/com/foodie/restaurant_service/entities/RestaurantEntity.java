package com.foodie.restaurant_service.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Entity
@Data
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    private String deliveryTime;
    private String openingTime;
    private String closingTime;
    private String city;
    private Integer rating;
    private Boolean isFavourite;
    private String imageType;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    @OneToMany(mappedBy = "restaurant",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<MenuEntity> menuItems;

}
