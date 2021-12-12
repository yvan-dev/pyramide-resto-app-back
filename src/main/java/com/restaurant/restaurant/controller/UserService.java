package com.restaurant.restaurant.controller;

import java.util.List;
import java.util.Optional;

import com.restaurant.restaurant.dao.UserDao;
import com.restaurant.restaurant.interfac.UserServiceInterface;
import com.restaurant.restaurant.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserService implements UserServiceInterface {
    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> addUser(User user) {
        if (user.getEmail() == "" || user.getPassword() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'email et le mot de passe sont requis!");
        if (userDao.findByEmail(user.getEmail()) != null)
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                    .body("Le mail " + user.getEmail() + " à déjà été attribué"); // 208
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userDao.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateUser(User user) {
        if (!userDao.existsById(user.getId()))
            return ResponseEntity.notFound().build();
        if (user.getPassword() != "")
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userDao.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(int idUser) {
        User user = userDao.findById(idUser);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        userDao.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<User>> getAllUser() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userDao.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Optional<User>> getUser(Integer idUser) {
        Optional<User> user = userDao.findById(idUser);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    public ResponseEntity<User> getUser(String token) {
        User user = userDao.findByToken(token);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
