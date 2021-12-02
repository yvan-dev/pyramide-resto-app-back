package com.restaurant.restaurant.interfac;

import com.restaurant.restaurant.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface AuthenticateServiceInterface {
    @PostMapping
    public ResponseEntity<User> login(@RequestBody User user);
}
