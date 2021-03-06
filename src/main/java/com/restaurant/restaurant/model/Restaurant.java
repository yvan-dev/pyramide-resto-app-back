package com.restaurant.restaurant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuppressWarnings("unused")
public class Restaurant {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String address;
}
