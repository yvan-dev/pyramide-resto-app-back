package com.restaurant.restaurant.dao;

import com.restaurant.restaurant.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface UserDao extends JpaRepository<User, Integer>{
    User findById(int id);

    User findByEmail(String email);

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

}
