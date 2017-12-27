package com.kaishengit.serivce;

import com.kaishengit.pojo.Movie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MoviceService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "findByIdFallback",threadPoolProperties = {
            @HystrixProperty(name = "coreSize",value = "30")
    })
    public Movie findById(Integer id) {
        String url = "http://MOVIE-SERVICE-PROVIDER/movie/"+id;
        return restTemplate.getForObject(url,Movie.class);
    }

    public Movie findByIdFallback(Integer id) {
        return new Movie(-1,null,null);
    }

}
