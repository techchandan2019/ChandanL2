package com.neosoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsProjL203EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProjL203EurekaServerApplication.class, args);
	}

}
