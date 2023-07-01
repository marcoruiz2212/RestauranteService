package com.co.restaurant.RestauranteService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ActiveProfiles("dev")
public class RestauranteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteServiceApplication.class, args);
	}

}
