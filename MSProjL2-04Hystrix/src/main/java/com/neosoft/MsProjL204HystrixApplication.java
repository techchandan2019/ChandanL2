package com.neosoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class MsProjL204HystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProjL204HystrixApplication.class, args);
	}

}
