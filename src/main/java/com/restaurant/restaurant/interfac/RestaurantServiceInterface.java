package com.restaurant.restaurant.interfac;

import java.util.Optional;

import com.restaurant.restaurant.model.Restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface RestaurantServiceInterface {
    @PostMapping
    ResponseEntity<String> addRestaurant(Restaurant restaurant);

    @PutMapping
    ResponseEntity<Void> updateRestaurant(Restaurant restaurant);

    @GetMapping("/restaurant/{id}")
    ResponseEntity<Optional<Restaurant>> getRestaurant(int id);
}
