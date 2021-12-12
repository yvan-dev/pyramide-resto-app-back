package com.restaurant.restaurant.model;

import java.time.LocalDate;

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
public class Food {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String type;
    @Column(columnDefinition = "varchar(999)")
    private String description;
    @Column(columnDefinition = "varchar(999)")
    private String ingredients;
    private LocalDate date;
    private String day;
}
