package com.example.order;


import com.example.order.service.cuncumer.UserConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@AllArgsConstructor
public class OrderApplication  implements CommandLineRunner{

	UserConsumerService userConsumerService;
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}


	@Override
	public void run(String... args) {


	}
}
