package com.restaurant.restaurant.controller;

import java.util.List;

import com.restaurant.restaurant.dao.TableDao;
import com.restaurant.restaurant.interfac.TableServiceInterface;
import com.restaurant.restaurant.model.Tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table")
public class TableService implements TableServiceInterface {

    @Autowired
    TableDao tableDao;

    @Override
    public ResponseEntity<String> addTable(Tables table) {
        if (String.valueOf(table.getIdRestaurant()) == "")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La table doit être rattaché à un resataurant");
        tableDao.save(table);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateTable(Tables table) {
        tableDao.save(table);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Tables>> getTableRestaurant(int idRestaurant) {
        return ResponseEntity.status(HttpStatus.OK).body(tableDao.findByIdRestaurant(idRestaurant));
    }

    @Override
    public ResponseEntity<List<Tables>> getTableAvailable(boolean isAvailable) {
        return ResponseEntity.status(HttpStatus.OK).body(tableDao.findByIsAvailable(isAvailable));
    }

}
