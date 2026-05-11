package com.unir.rental.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RentalVehicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalVehicleApplication.class, args);
	}

}
