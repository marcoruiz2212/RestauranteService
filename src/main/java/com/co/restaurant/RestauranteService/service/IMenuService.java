package com.co.restaurant.RestauranteService.service;

import com.co.restaurant.RestauranteService.model.dto.MenuDto;

public interface IMenuService {

     MenuDto saveMenu(final MenuDto menuDto);

     MenuDto getMenuById(Long id);
}
