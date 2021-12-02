package com.restaurant.restaurant.interfac;

import java.util.List;

import com.restaurant.restaurant.model.Tables;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface TableServiceInterface {
    @PostMapping
    ResponseEntity<String> addTable(Tables tables);
    @PutMapping
    ResponseEntity<Void> updateTable(Tables tables);
    @GetMapping("/id/{idRestaurant}")
    ResponseEntity<List<Tables>> getTableRestaurant(int idRestaurant);
    @GetMapping("/available/{isAvailable}")
    ResponseEntity<List<Tables>> getTableAvailable(boolean isAvailable);
}
