package com.co.restaurant.RestauranteService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRestaurantDTO {

    private Long id;
    private String comment;

    private RestaurantDto restaurant;
}
