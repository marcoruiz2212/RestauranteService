package com.co.restaurant.RestauranteService.service.impl;

import com.co.restaurant.RestauranteService.model.Menu;
import com.co.restaurant.RestauranteService.model.Restaurant;
import com.co.restaurant.RestauranteService.model.dto.MenuDto;
import com.co.restaurant.RestauranteService.model.dto.RestaurantDto;
import com.co.restaurant.RestauranteService.repository.MenuRepository;
import com.co.restaurant.RestauranteService.service.IMenuService;
import com.co.restaurant.RestauranteService.service.IRestaurantService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements IMenuService {

    public static Function<MenuDto, Menu> populateMenu = (menuDto) -> Menu.builder()
            .name(menuDto.getName())
            .description(menuDto.getDescription())
            .cost(menuDto.getCost())
            .price(menuDto.getPrice())
            .build();

    public static Function<Menu, MenuDto> populateMenuDto = menu -> MenuDto.builder()
            .id(menu.getId())
            .name(menu.getName())
            .description(menu.getDescription())
            .cost(menu.getCost())
            .price(menu.getPrice()).build();

    public static BiFunction<MenuDto, Restaurant, Menu> populateMenuWithRestaurant = (menuDto, restaurant) -> Menu.builder()
            .name(menuDto.getName())
            .description(menuDto.getDescription())
            .cost(menuDto.getCost())
            .price(menuDto.getPrice())
            .restaurant(restaurant)
            .build();

    private final MenuRepository menuRepository;

    private final IRestaurantService restaurantService;

    public MenuDto saveMenu(final MenuDto menuDto){
        if(Objects.isNull(menuDto.getRestaurant()) && menuDto.getRestaurant().getId() == null){
            final RestaurantDto restaurantDto = restaurantService.getRestaurantById(menuDto.getRestaurant().getId());
            if(Objects.isNull(restaurantDto)){
                throw new NullPointerException("Restaurant doesn't exists");
            }
        }
        final Menu menu = populateMenu.apply(menuDto);
        final Menu menuSaved = menuRepository.save(menu);
        menuDto.setId(menuSaved.getId());
        return menuDto;
    }


    public MenuDto getMenuById(Long id){
       Optional<Menu> menu = menuRepository.findById(id);
       return populateMenuDto.apply(menu.get());

    }
}
