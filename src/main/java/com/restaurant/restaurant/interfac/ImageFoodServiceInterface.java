package com.restaurant.restaurant.interfac;

import java.util.List;

import com.restaurant.restaurant.model.ImageFood;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface ImageFoodServiceInterface {
    @PostMapping
    ResponseEntity<String> addImageFood(ImageFood imageFood);

    @PutMapping
    ResponseEntity<Void> updateImageFood(ImageFood imageFood);

    @GetMapping("/id/{idFood}")
    ResponseEntity<List<ImageFood>> getImagesFood(@PathVariable int idFood);
}