package com.restaurant.restaurant.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestDate {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        System.out.println("date = " + formatter.format(date));
        System.out.println("date of first day : " + formatter.format(date.with(DayOfWeek.MONDAY)));
        System.out.println("DayOfWeek: " + date.getDayOfWeek().toString());
        System.out.println("DayOfYear: " + date.getDayOfYear());
        System.out.println("DayOfMonth: " + date.getDayOfMonth());
        System.out.println("Chronology: " + date.getChronology());
        System.out.println("ERA: " + date.getEra());
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            System.out.println("DayOfWeek.values() : " + DayOfWeek.values()[i]) ;
        }
        System.out.println("DayOfWeek value: " + date.getDayOfWeek().getValue());
    }
}
