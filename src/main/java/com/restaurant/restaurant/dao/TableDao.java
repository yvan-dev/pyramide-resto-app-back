package com.restaurant.restaurant.dao;

import java.util.List;

import com.restaurant.restaurant.model.Tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface TableDao extends JpaRepository<Tables, Integer> {
    Tables findById(int id);

    List<Tables> findByIdRestaurant(int idRestaurant);

    List<Tables> findByIsAvailable(boolean isAvailable);;

}
