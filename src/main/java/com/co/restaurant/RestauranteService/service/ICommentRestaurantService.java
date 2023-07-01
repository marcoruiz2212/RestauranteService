package com.co.restaurant.RestauranteService.service;

import com.co.restaurant.RestauranteService.model.dto.CommentRestaurantDTO;

import java.util.List;

public interface ICommentRestaurantService {

    CommentRestaurantDTO saveComment(CommentRestaurantDTO commentRestaurant, Long restaurantId, Long userId);

    List<CommentRestaurantDTO> getAllCommentByRestaurant(final String comment, final Long id);
}
