package com.co.restaurant.RestauranteService.repository;


import com.co.restaurant.RestauranteService.model.CommentRestaurant;
import com.co.restaurant.RestauranteService.model.Restaurant;
import com.co.restaurant.RestauranteService.model.dto.CommentRestaurantDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRestaurantRepository extends JpaRepository<CommentRestaurant, Long> {

    List<CommentRestaurant> findByCommentContainsAndRestaurant(String comment, Restaurant restaurant);


}
