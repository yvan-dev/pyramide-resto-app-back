package com.restaurant.restaurant.dao;

import java.util.Optional;

import com.restaurant.restaurant.model.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface RestaurantDao extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findById(Integer id);

    Restaurant findByName(String name);

    Restaurant findByAddress(String address);
}
