package com.kaishengit.controller;

import com.kaishengit.pojo.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MovieController {

    private Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Value("${qiniu.key}")
    private String qiniuKey;

    @GetMapping("/qn")
    public String qiniu() {
        return qiniuKey;
    }

    @GetMapping("/movie/{id:\\d+}")
    public Movie findMovie(@PathVariable Integer id) {
        return new Movie(1001,"大话西游","周星驰");
    }

    @PostMapping("/movie/new")
    public String save(String name,String actor) {
        logger.info("Movie Name:{}  -> Actor:{} ",name,actor);
        return "success";
    }
}
