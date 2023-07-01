package com.co.restaurant.RestauranteService.service;

import com.co.restaurant.RestauranteService.model.City;
import com.co.restaurant.RestauranteService.model.dto.CityDto;
import com.co.restaurant.RestauranteService.repository.CityRepository;
import com.co.restaurant.RestauranteService.service.impl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    private CityDto cityDto;

    private City city;

    @BeforeEach
    public void init(){
        cityDto = CityDto.builder().name("test").build();
        city = City.builder().id(1l).name("test").build();
    }


    @Test
    public void saveCityTest(){
        Mockito.when(cityRepository.save(any(City.class))).thenReturn(city);
        cityDto = cityService.saveCity(cityDto);
        Assert.notNull(cityDto);
        assertEquals(cityDto.getName(), city.getName());

    }
}
