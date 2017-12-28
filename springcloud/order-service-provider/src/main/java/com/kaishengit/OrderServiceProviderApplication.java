package com.kaishengit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceProviderApplication.class, args);
	}
}
