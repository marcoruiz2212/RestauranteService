package com.co.restaurant.RestauranteService.controller;

import com.co.restaurant.RestauranteService.model.dto.MenuDto;
import com.co.restaurant.RestauranteService.service.IMenuService;
import com.co.restaurant.RestauranteService.service.IRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final IMenuService menuService;


    @PostMapping
    public ResponseEntity<MenuDto> saveMenu(@RequestBody final MenuDto menu){
        final MenuDto menuDto = menuService.saveMenu(menu);
        return ResponseEntity.ok(menuDto);
    }

    @GetMapping
    public ResponseEntity< MenuDto> getMenu(@RequestParam final Long id){
        final MenuDto menuDto = menuService.getMenuById(id);
        return ResponseEntity.ok(menuDto);
    }
}
