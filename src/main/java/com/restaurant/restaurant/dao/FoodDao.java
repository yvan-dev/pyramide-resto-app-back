package com.restaurant.restaurant.dao;

import java.time.LocalDate;
import java.util.List;

import com.restaurant.restaurant.model.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface FoodDao extends JpaRepository<Food, Integer> {
    Food findById(int id);

    List<Food> findByName(String name);

    List<Food> findByDate(LocalDate date);

    List<Food> findByDay(String day);

    Food findByNameAndDate(String name, LocalDate date);
}
