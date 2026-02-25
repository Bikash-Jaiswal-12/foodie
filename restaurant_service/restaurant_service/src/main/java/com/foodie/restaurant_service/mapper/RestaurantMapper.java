package com.foodie.restaurant_service.mapper;

import com.foodie.restaurant_service.dto.MenuResponseDto;
import com.foodie.restaurant_service.dto.RestaurantRequestDto;
import com.foodie.restaurant_service.dto.RestaurantResponseDto;
import com.foodie.restaurant_service.entities.RestaurantEntity;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public RestaurantEntity toEntity(RestaurantRequestDto dto){
        RestaurantEntity r=new RestaurantEntity();
        r.setName(dto.getName());
        r.setCity(dto.getCity());
        r.setOpeningTime(dto.getOpeningTime());
        r.setClosingTime(dto.getClosingTime());
        return r;
    }

    public RestaurantResponseDto toDTO(RestaurantEntity r){

        RestaurantResponseDto dto=new RestaurantResponseDto();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setCity(r.getCity());
        dto.setOpeningTime(r.getOpeningTime());
        dto.setClosingTime(r.getClosingTime());

        if(r.getMenuItems()!=null){
            dto.setMenu(r.getMenuItems().stream().map(m->{
                MenuResponseDto md=new MenuResponseDto();
                md.setId(m.getId());
                md.setName(m.getName());
                md.setPrice(m.getPrice());
                md.setDescription(m.getDescription());
                md.setCategory(m.getCategory());
                return md;
            }).toList());
        }
        return dto;
    }
}
