package com.restaurant.restaurant.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.restaurant.restaurant.dao.UserDao;
import com.restaurant.restaurant.interfac.AuthenticateServiceInterface;
import com.restaurant.restaurant.model.User;
import com.restaurant.restaurant.model.UserAuthenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/login")
public class AuthenticateService implements AuthenticateServiceInterface {

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<User> login(UserAuthenticate user) {
        User userDb = userDao.findByEmail(user.getEmail());
        if (userDb == null || !BCrypt.checkpw(user.getPassword(), userDb.getPassword()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        String token = getJWTToken(user.getEmail());
        userDb.setToken(token);
        userDao.save(userDb);
        return ResponseEntity.status(HttpStatus.OK).body(userDb);
    }

    private String getJWTToken(String login) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER, ADMIN_USER");

		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(login)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000)) // 3 jours
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
