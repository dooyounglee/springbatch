package com.doo.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class BatchApplication {

	public static void main(String[] args) {
		// SpringApplication.run(BatchApplication.class, args);
		new SpringApplication(BatchApplication.class).run(args);
	}
	
}
