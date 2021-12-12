package com.restaurant.restaurant.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.restaurant.restaurant.dao.FoodDao;
import com.restaurant.restaurant.interfac.FoodServiceInterface;
import com.restaurant.restaurant.model.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodService implements FoodServiceInterface {

    @Autowired
    FoodDao foodDao;

    @Override
    public ResponseEntity<String> addFood(Food food) {
        if (food.getName() == "")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du plat est requis !");
        if (foodDao.findByName(food.getName()) != null)
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Le plat existe déjà!");
        foodDao.save(food);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateFood(Food food) {
        if (!foodDao.existsById(food.getId()))
            return ResponseEntity.notFound().build();
        foodDao.save(food);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Food>> getAllFood() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(foodDao.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Food> getFood(int idFood) {
        Food food = foodDao.findById(idFood);
        if (food != null)
            return ResponseEntity.status(HttpStatus.OK).body(food);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<List<Food>> getFoodOfCurrentDay() {
        List<Food> foods = foodDao.findByDate(LocalDate.now());
        if (foods.size() == 0)
            return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(foods);
    }

    @Override
    public ResponseEntity<List<Food>> getFoodOfCurrentWeek() {
        ArrayList<Food> foodsOfWeek = new ArrayList<Food>();
        LocalDate now = LocalDate.now();
        for (DayOfWeek day : DayOfWeek.values()) {
            now = now.with(DayOfWeek.valueOf(day.toString()));
            List<Food> foods = foodDao.findByDate(now);
            if (foods.size() != 0)
                foodsOfWeek.addAll(foods);
        }
        if (foodsOfWeek.size() == 0)
            return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(foodsOfWeek);
    }

}
