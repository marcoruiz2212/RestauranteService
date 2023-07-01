package com.co.restaurant.RestauranteService.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class MenuDto {

    private Long id;

    private String name;

    private String description;

    private Double cost;

    private Double price;

    private RestaurantDto restaurant;
}
