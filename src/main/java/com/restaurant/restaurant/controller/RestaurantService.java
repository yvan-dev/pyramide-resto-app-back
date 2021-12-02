package com.restaurant.restaurant.controller;

import java.util.Optional;

import com.restaurant.restaurant.dao.RestaurantDao;
import com.restaurant.restaurant.interfac.RestaurantServiceInterface;
import com.restaurant.restaurant.model.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantService implements RestaurantServiceInterface {

    @Autowired
    RestaurantDao restaurantDao;

    @Override
    public ResponseEntity<String> addRestaurant(Restaurant restaurant) {
        if (restaurant.getName() == "")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du restaurant est requis");
        if (restaurantDao.findByName(restaurant.getName()) != null)
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                    .body("Le restaurant " + restaurant.getName() + " existe déjà!");
        restaurantDao.save(restaurant);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> updateRestaurant(Restaurant restaurant) {
        restaurantDao.save(restaurant);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Optional<Restaurant>> getRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantDao.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

}
