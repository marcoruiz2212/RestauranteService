package com.co.restaurant.RestauranteService.service;

import com.co.restaurant.RestauranteService.model.dto.RestaurantDto;

public interface IRestaurantService {
    RestaurantDto createRestaurant(RestaurantDto restaurantDto);

    RestaurantDto getRestaurantById(final Long id);
}
