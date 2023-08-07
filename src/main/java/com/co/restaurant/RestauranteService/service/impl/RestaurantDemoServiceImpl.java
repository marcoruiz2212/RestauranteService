package com.co.restaurant.RestauranteService.service.impl;

import com.co.restaurant.RestauranteService.model.dto.RestaurantDto;
import com.co.restaurant.RestauranteService.service.IRestaurantService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("!default")
public class RestaurantDemoServiceImpl implements IRestaurantService {
    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        return RestaurantDto.builder().id(1l).name("demo").build();
    }

    @Override
    public RestaurantDto getRestaurantById(Long id) {
        return RestaurantDto.builder().id(1l).name("demo").build();
    }

    @Override
    public Set<RestaurantDto> getAllRestaurant() {
        return null;
    }
}
