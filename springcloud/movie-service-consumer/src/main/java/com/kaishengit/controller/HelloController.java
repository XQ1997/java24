package com.kaishengit.controller;

import com.kaishengit.MovieServiceConsumerApplication;
import com.kaishengit.client.MovieClient;
import com.kaishengit.pojo.Movie;
import com.kaishengit.serivce.MoviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    //@Autowired
    //private RestTemplate restTemplate;
    //@Autowired
    //private LoadBalancerClient loadBalancerClient;
    /*@Autowired
    private MovieClient movieClient;*/
    @Autowired
    private MoviceService moviceService;

    @GetMapping("/hello")
    public Movie hello() {
        //ServiceInstance serviceInstance = loadBalancerClient.choose("MOVIE-SERVICE-PROVIDER");
        //return restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/movie/123",Movie.class);
        //return restTemplate.getForObject("http://MOVIE-SERVICE-PROVIDER/movie/123",Movie.class);
        //return movieClient.findById(123);
        return moviceService.findById(123);
    }

    /*@GetMapping("/save")
    public String save() {
        return movieClient.saveMovie("无间道","刘德华");
    }*/
}
