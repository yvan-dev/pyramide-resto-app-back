package com.restaurant.restaurant.interfac;

import com.restaurant.restaurant.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user);

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody User user);

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable int idUser);

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser();

    @GetMapping("/id/{idUser}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Integer idUser);

    @GetMapping("/token/{token}")
    public ResponseEntity<User> getUser(@PathVariable String token);
}
