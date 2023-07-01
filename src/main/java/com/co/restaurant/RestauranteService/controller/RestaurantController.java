package com.co.restaurant.RestauranteService.controller;

import com.co.restaurant.RestauranteService.model.dto.RestaurantDto;
import com.co.restaurant.RestauranteService.service.IMenuService;
import com.co.restaurant.RestauranteService.service.IRestaurantService;
import com.co.restaurant.RestauranteService.service.impl.RestaurantServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {


    private final IRestaurantService restaurantService;

    @PostMapping
    @Operation(summary = "Creates a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created restaurant"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurant){
        final RestaurantDto restaurantDto = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(restaurantDto);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "get restaurant by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable final Long id){
        final RestaurantDto restaurantDto = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantDto);
    }





}
