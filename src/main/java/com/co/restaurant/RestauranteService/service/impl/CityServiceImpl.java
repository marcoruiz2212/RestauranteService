package com.co.restaurant.RestauranteService.service.impl;

import com.co.restaurant.RestauranteService.model.City;
import com.co.restaurant.RestauranteService.model.dto.CityDto;
import com.co.restaurant.RestauranteService.model.dto.MenuDto;
import com.co.restaurant.RestauranteService.repository.CityRepository;
import com.co.restaurant.RestauranteService.service.ICityService;
import com.co.restaurant.RestauranteService.service.IMenuService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements ICityService {


    private final CityRepository cityRepository;
    @Override
    public CityDto saveCity(CityDto cityDto) {
        City city = City.builder().name(cityDto.getName()).build();
        cityRepository.save(city);
        cityDto.setId(city.getId());
        return cityDto;
    }
}
