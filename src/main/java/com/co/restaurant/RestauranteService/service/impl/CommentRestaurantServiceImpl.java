package com.co.restaurant.RestauranteService.service.impl;

import com.co.restaurant.RestauranteService.model.CommentRestaurant;
import com.co.restaurant.RestauranteService.model.Menu;
import com.co.restaurant.RestauranteService.model.Restaurant;
import com.co.restaurant.RestauranteService.model.dto.CommentRestaurantDTO;
import com.co.restaurant.RestauranteService.model.dto.MenuDto;
import com.co.restaurant.RestauranteService.model.dto.RestaurantDto;
import com.co.restaurant.RestauranteService.repository.CommentRestaurantRepository;
import com.co.restaurant.RestauranteService.service.ICommentRestaurantService;
import com.co.restaurant.RestauranteService.service.IRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentRestaurantServiceImpl implements ICommentRestaurantService {

    public static Function<CommentRestaurant, CommentRestaurantDTO> populateCommentRestaurant = comment -> CommentRestaurantDTO.builder()
            .id(comment.getId())
            .comment(comment.getComment()).build();

    private final CommentRestaurantRepository commentRestaurantRepository;

    private final IRestaurantService restaurantService;

    @Override
    public CommentRestaurantDTO saveComment(final CommentRestaurantDTO commentRestaurant,
                                            final Long restaurantId, final Long userId) {
        final RestaurantDto restaurantDto = restaurantService.getRestaurantById(restaurantId);
        if(Objects.isNull(restaurantDto)){
            throw new NullPointerException(String.format("Restaurant with id: %s doesn't exists", restaurantId));
        }
        CommentRestaurant commentRestaurantSave = CommentRestaurant.builder().userId(userId)
                .comment(commentRestaurant.getComment()).restaurant(Restaurant.builder().id(restaurantId).build()).build();
        commentRestaurantSave = commentRestaurantRepository.save(commentRestaurantSave);

        commentRestaurant.setId(commentRestaurantSave.getId());
        commentRestaurant.setRestaurant(restaurantDto);
        return commentRestaurant;
    }

    public List<CommentRestaurantDTO> getAllCommentByRestaurant(final String comment, final Long id){
        final List<CommentRestaurant> comments = commentRestaurantRepository.findByCommentContainsAndRestaurant(comment,
                                                                                Restaurant.builder().id(id).build());
        return comments.stream().map(populateCommentRestaurant).collect(Collectors.toList());
    }
}
