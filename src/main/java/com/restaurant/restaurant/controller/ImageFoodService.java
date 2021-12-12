package com.restaurant.restaurant.controller;

import java.util.List;

import com.restaurant.restaurant.dao.ImageFoodDao;
import com.restaurant.restaurant.interfac.ImageFoodServiceInterface;
import com.restaurant.restaurant.model.ImageFood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imageFood")
public class ImageFoodService implements ImageFoodServiceInterface {

    @Autowired
    ImageFoodDao imageFoodDao;

    @Override
    public ResponseEntity<String> addImageFood(ImageFood imageFood) {
        if (String.valueOf(imageFood.getIdFood()) == "")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'image doit être rattachée à un plat");
        if (imageFood.getImageUrl() == null)
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("L'image est requis");
        /* Possible de comparer deux bytes ?? Flemme de chercher */
        if (imageFoodDao.findByImageUrl(imageFood.getImageUrl()) != null)
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("L'image existe déjà");
        imageFoodDao.save(imageFood);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateImageFood(ImageFood imageFood) {
        if (!imageFoodDao.existsById(imageFood.getIdFood()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        imageFoodDao.save(imageFood);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<ImageFood>> getImagesFood(int idFood) {
        return ResponseEntity.status(HttpStatus.OK).body(imageFoodDao.findByIdFood(idFood));
    }
}
