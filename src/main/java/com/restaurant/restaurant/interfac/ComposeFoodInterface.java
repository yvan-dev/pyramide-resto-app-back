package com.restaurant.restaurant.interfac;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ComposeFoodInterface {
    @GetMapping("/day")
    public ResponseEntity<String> composeFoodOfDay();
    @GetMapping("/day/{day}")
    public ResponseEntity<String> composeFoodOfDay(@PathVariable String day);
    @GetMapping("/week")
    public ResponseEntity<String> composeFoodOfWeek();
}
