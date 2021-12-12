package com.restaurant.restaurant.dao;

import java.util.List;

import com.restaurant.restaurant.model.ImageFood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface ImageFoodDao extends JpaRepository<ImageFood, Integer> {
    ImageFood findById(int id);

    ImageFood findByName(String name);

    ImageFood findByImageUrl(String imageUrl);

    List<ImageFood> findByIdFood(int idFood);
}
