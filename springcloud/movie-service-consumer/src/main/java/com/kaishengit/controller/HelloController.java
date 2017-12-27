package com.kaishengit.controller;

import com.kaishengit.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public Movie hello() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("MOVIE-SERVICE-PROVIDER");
        return restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/movie/123",Movie.class);
    }
}
