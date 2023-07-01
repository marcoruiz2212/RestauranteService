package com.co.restaurant.RestauranteService.controller;

import com.co.restaurant.RestauranteService.model.dto.CityDto;
import com.co.restaurant.RestauranteService.service.ICityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/city")
@RequiredArgsConstructor
public class CityController {

    private final ICityService cityService;

    @PostMapping
    @Operation(summary = "Create new city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    public ResponseEntity<CityDto> createCity(@RequestBody final CityDto cityDto){
        final CityDto city = cityService.saveCity(cityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(city);
    }
}
