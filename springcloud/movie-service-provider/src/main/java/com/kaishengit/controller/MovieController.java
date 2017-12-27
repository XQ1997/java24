package com.kaishengit.controller;

import com.kaishengit.pojo.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/movie/{id:\\d+}")
    public Movie findMovie(@PathVariable Integer id) {
        return new Movie(1001,"大话西游","周星驰");
    }

}
