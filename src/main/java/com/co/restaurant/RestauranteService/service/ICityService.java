package com.co.restaurant.RestauranteService.service;

import com.co.restaurant.RestauranteService.model.dto.CityDto;

import java.util.Set;

public interface ICityService {

    CityDto saveCity(CityDto city);

    Set<CityDto> getAll();

    CityDto getACityById(Long id);
}
