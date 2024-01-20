package com.neosoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@SpringBootApplication
public class BootProjL206SpringBootKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootProjL206SpringBootKafkaConsumerApplication.class, args);
	}

}
