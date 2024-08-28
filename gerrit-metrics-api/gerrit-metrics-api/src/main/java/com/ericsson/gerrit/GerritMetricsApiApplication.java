package com.ericsson.gerrit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerritMetricsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerritMetricsApiApplication.class, args);
	}

}
