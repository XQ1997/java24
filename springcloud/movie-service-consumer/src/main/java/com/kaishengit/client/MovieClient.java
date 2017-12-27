package com.kaishengit.client;

import com.kaishengit.pojo.Movie;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MOVIE-SERVICE-PROVIDER")
public interface MovieClient {

    @GetMapping("/movie/{id}")
    Movie findById(@PathVariable(name = "id") Integer id);
    @PostMapping("/movie/new")
    String saveMovie(@RequestParam(name = "name") String name,
                     @RequestParam(name = "actor") String actor);
}
