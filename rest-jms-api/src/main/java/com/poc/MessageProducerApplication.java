package com.poc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableBatchProcessing
@EnableCaching
public class MessageProducerApplication {

	public static void main(String[] args) { // Launch application
		SpringApplication.run(MessageProducerApplication.class, args); // get JMS
	}

}
