package com.kaishengit;

import com.kaishengit.filter.MyZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ApiGatewayApplication {

    /**
     * 配置自定义的Zuul过滤器
     * @return
     */
    @Bean
    public MyZuulFilter myZuulFilter() {
        return new MyZuulFilter();
    }


	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
