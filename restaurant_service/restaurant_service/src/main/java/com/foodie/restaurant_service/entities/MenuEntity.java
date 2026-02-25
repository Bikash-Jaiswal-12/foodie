package com.foodie.restaurant_service.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "menu_item")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private RestaurantEntity restaurant;
}
