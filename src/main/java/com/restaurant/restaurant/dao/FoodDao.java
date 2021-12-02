package com.restaurant.restaurant.dao;

import com.restaurant.restaurant.model.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface FoodDao extends JpaRepository<Food, Integer> {
    Food findById(int id);

    Food findByName(String name);
}
