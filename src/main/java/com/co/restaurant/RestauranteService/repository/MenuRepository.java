package com.co.restaurant.RestauranteService.repository;

import com.co.restaurant.RestauranteService.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
