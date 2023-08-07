package com.co.restaurant.RestauranteService.service.impl;

import com.co.restaurant.RestauranteService.model.City;
import com.co.restaurant.RestauranteService.model.Menu;
import com.co.restaurant.RestauranteService.model.Restaurant;
import com.co.restaurant.RestauranteService.model.dto.CityDto;
import com.co.restaurant.RestauranteService.model.dto.MenuDto;
import com.co.restaurant.RestauranteService.model.dto.RestaurantDto;
import com.co.restaurant.RestauranteService.repository.RestaurantRepository;
import com.co.restaurant.RestauranteService.service.IRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Profile("default")
@Slf4j
public class RestaurantServiceImpl implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;



    private Function<RestaurantDto, Restaurant> populateRestaurant = restaurantDto ->{
        final Restaurant restaurant =Restaurant.builder()
                                                .name(restaurantDto.getName())
                                                .city(City.builder().id(restaurantDto.getCity().getId()).build())
                                                .address(restaurantDto.getAddress())
                                                .phone(restaurantDto.getPhone()).build();

        List<Menu> menuList = new ArrayList<>();
        if(restaurantDto.getMenus()!=null && restaurantDto.getMenus().size()>0){
            for(MenuDto menuDto: restaurantDto.getMenus()){
                final Menu menu = MenuServiceImpl.populateMenu.apply(menuDto);
                menu.setRestaurant(restaurant);
                menuList.add(menu);
            }
            restaurant.setMenuList(menuList);
        }
        return restaurant;

    };

    private Function<Restaurant, RestaurantDto> populateRestaurantDtoFunction = restaurant ->
         RestaurantDto.builder().id(restaurant.getId())
                .name(restaurant.getName())
                .phone(restaurant.getPhone())
                .address(restaurant.getAddress())
                 .city(CityDto.builder().id(restaurant.getCity().getId()).name(restaurant.getCity().getName()).build())
                .build();




    public RestaurantDto createRestaurant(final RestaurantDto restaurantDto){
        log.info("******create restaurant******** ");
        final Restaurant restaurant = populateRestaurant.apply(restaurantDto);
        final Restaurant restaurantCreated = restaurantRepository.save(restaurant);
        log.info("******restaurant saved succesfully******** ");
        return getRestaurant(restaurantCreated);
    }

    public RestaurantDto getRestaurantById(final Long id){
        final Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isEmpty()){
            throw new NullPointerException("Restaurant doesn't exist");
        }
        return getRestaurant(restaurant.get());
    }

    @Override
    public Set<RestaurantDto> getAllRestaurant() {
        final List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(this::getRestaurant).collect(Collectors.toSet());
    }


    public List<String> getNamesRestaurant(){
        List<String> titles = new ArrayList<>();
        List<Restaurant> restaurants = restaurantRepository.findAll();
        if(restaurants.isEmpty()) return titles;

        titles = restaurants.stream().map(Restaurant::getName).collect(Collectors.toList());
        return titles;
    }

    private RestaurantDto getRestaurant(final Restaurant restaurant){
        RestaurantDto restaurantDto = populateRestaurantDtoFunction.apply(restaurant);
        if(restaurant.getMenuList() != null && restaurant.getMenuList().size()>0){
            restaurantDto.setMenus(restaurant.getMenuList().stream().map(MenuServiceImpl.populateMenuDto).collect(Collectors.toList()));
        }

        if(restaurant.getCommentRestaurants()!=null && restaurant.getCommentRestaurants().size()>0){
            restaurantDto.setComments(restaurant.getCommentRestaurants().stream().map(CommentRestaurantServiceImpl.populateCommentRestaurant).collect(Collectors.toList()));
        }
        return restaurantDto;
    }

}
