package com.restaurant.restaurant.interfac;

import java.util.List;

import com.restaurant.restaurant.model.Food;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface FoodServiceInterface {
    @PostMapping
    public ResponseEntity<String> addFood(Food food);
    @PutMapping
    public ResponseEntity<Void> updateFood(Food food);
    @GetMapping("/all")
    public ResponseEntity<List<Food>> getAllFood();
    @GetMapping("/id/{idFood}")
    public ResponseEntity<Food> getFood(int idFood);
    @GetMapping("/day")
    public ResponseEntity<List<Food>> getFoodOfCurrentDay();
    @GetMapping("/week")
    public ResponseEntity<List<Food>> getFoodOfCurrentWeek();
}
