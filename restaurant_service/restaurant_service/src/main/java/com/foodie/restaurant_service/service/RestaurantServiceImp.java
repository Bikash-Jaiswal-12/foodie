package com.foodie.restaurant_service.service;

import com.foodie.restaurant_service.dto.MenuRequestDto;
import com.foodie.restaurant_service.dto.MenuResponseDto;
import com.foodie.restaurant_service.dto.RestaurantRequestDto;
import com.foodie.restaurant_service.dto.RestaurantResponseDto;
import com.foodie.restaurant_service.entities.MenuEntity;
import com.foodie.restaurant_service.entities.RestaurantEntity;
import com.foodie.restaurant_service.mapper.RestaurantMapper;
import com.foodie.restaurant_service.repository.MenuRepository;
import com.foodie.restaurant_service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantServiceImp implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final RestaurantMapper mapper;

    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto dto) {
        RestaurantEntity r=mapper.toEntity(dto);
        return mapper.toDTO(
                restaurantRepository.save(r));
    }

    @Override
    public MenuResponseDto addMenu(Long restaurantId, MenuRequestDto dto) {
        RestaurantEntity r=restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new RuntimeException("Not found"));

        MenuEntity item=new MenuEntity();
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setCategory(dto.getCategory());
        item.setDescription(dto.getDescription());
        item.setRestaurant(r);

        MenuEntity saved=menuRepository.save(item);

        MenuResponseDto res=new MenuResponseDto();
        res.setId(saved.getId());
        res.setName(saved.getName());
        res.setPrice(saved.getPrice());
        res.setDescription(saved.getDescription());
        res.setCategory(saved.getCategory());

        return res;
    }

    @Override
    public RestaurantResponseDto getRestaurant(Long id) {
        RestaurantEntity r=restaurantRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Not found"));

        return mapper.toDTO(r);
    }

    @Override
    public void uploadRestaurantImage(Long id, MultipartFile file) throws IOException {
        RestaurantEntity r=restaurantRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Not found"));

        r.setImage(file.getBytes());
        r.setImageType(file.getContentType());
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        List<RestaurantEntity> restaurants =
                restaurantRepository.findAll();

        return restaurants.stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void deleteRestaurant(Long id) {
        RestaurantEntity restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Restaurant not found"));

        restaurantRepository.delete(restaurant);
    }

    @Override
    @Transactional
    public RestaurantResponseDto updateRestaurant(Long id, RestaurantRequestDto dto) {
        RestaurantEntity
                restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Restaurant not found"));

        // ✅ Update only provided fields
        if(dto.getName()!=null){
            restaurant.setName(dto.getName());
        }

        if(dto.getCity()!=null){
            restaurant.setCity(dto.getCity());
        }

        if(dto.getClosingTime()!=null){
            restaurant.setClosingTime(dto.getClosingTime());
        }
        if (dto.getOpeningTime()!=null){
            restaurant.setOpeningTime(dto.getOpeningTime());
        }

        RestaurantEntity saved =
                restaurantRepository.save(restaurant);

        return mapper.toDTO(saved);
    }

    @Override
    @Transactional
    public void deleteMenuItem(Long id) {
        MenuEntity menuItem = menuRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Menu Item not found"));
        menuRepository.delete(menuItem);

    }

    @Override
    public MenuResponseDto updateMenu(Long id, MenuRequestDto dto) {
        MenuEntity
                menu =
                menuRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Menu Item not found"));

        // ✅ Update only provided fields
        if(dto.getName()!=null){
            menu.setName(dto.getName());
        }

        if(dto.getPrice()!=null){
            menu.setPrice(dto.getPrice());
        }

        if(dto.getDescription()!=null){
            menu.setDescription(dto.getDescription());
        }
        if (dto.getCategory()!=null){
            menu.setCategory(dto.getCategory());
        }

        MenuEntity saved =
                menuRepository.save(menu);

        MenuResponseDto response = new MenuResponseDto();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setPrice(saved.getPrice());
        response.setDescription(saved.getDescription());
        response.setCategory(saved.getCategory());

        return response;
    }

}
