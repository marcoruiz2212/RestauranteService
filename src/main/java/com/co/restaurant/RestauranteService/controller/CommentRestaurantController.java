package com.co.restaurant.RestauranteService.controller;

import com.co.restaurant.RestauranteService.model.dto.CommentRestaurantDTO;
import com.co.restaurant.RestauranteService.service.ICommentRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentRestaurantController {

    private final ICommentRestaurantService commentRestaurantService;

    @PostMapping("/{id}")
    public ResponseEntity<CommentRestaurantDTO> saveCommentRestaurant(@RequestBody final CommentRestaurantDTO commentRestaurant,
                                                                      @PathVariable final Long id,
                                                                      @RequestHeader("x-user-id") final Long userId){
        final CommentRestaurantDTO comment = commentRestaurantService.saveComment(commentRestaurant,id, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<CommentRestaurantDTO>> getCommentsByRestaurant(@PathVariable final Long restaurantId, @RequestParam String content ){
        final List<CommentRestaurantDTO> comments = commentRestaurantService.getAllCommentByRestaurant(content, restaurantId);
        return ResponseEntity.ok(comments);
    }
}
