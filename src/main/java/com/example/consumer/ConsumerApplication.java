package com.example.consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableConfigServer
public class ConsumerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String serviceUrl = "http://localhost:8080/products";

		try {
			ResponseEntity<String> response = restTemplate.getForEntity(serviceUrl, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				System.out.println("Response from API: " + response.getBody());
			} else {
				System.out.println("Failed to fetch data from API. Status code: " + response.getStatusCode());
			}
		} catch (Exception e) {
			System.err.println("Error occurred while calling the API: " + e.getMessage());
		}
	}

}
