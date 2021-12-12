package com.restaurant.restaurant.model;

import javax.persistence.Column;
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
public class Tables {
    @Id
    @GeneratedValue
    private int id;
    private int idRestaurant;
    private int nbPlaces = 1;
    private boolean isAvailable = false;
}
