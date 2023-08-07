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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Set<CityDto> getAll() {
       final List<City> cityList = cityRepository.findAll();
       return cityList.stream().map(this::populateCity).collect(Collectors.toSet());
    }

    @Override
    public CityDto getACityById(final Long id) {
        final Optional<City> city = cityRepository.findById(id);
        if(city.isEmpty()) throw new NullPointerException(String.format("City with id %s doesn't exists", id));
        return populateCity(city.get());
    }

    private CityDto populateCity(final City city){
        return CityDto.builder().id(city.getId()).name(city.getName()).build();
    }


}
