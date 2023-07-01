package com.co.restaurant.RestauranteService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private Long id;

    private String name;

    private String address;

    private String phone;

    private CityDto city;

    private List<MenuDto> menus;

    private List<CommentRestaurantDTO> comments;
}
