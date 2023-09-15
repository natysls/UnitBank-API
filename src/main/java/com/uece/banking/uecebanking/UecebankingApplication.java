package com.uece.banking.uecebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.uece.banking.uecebanking.dto")
public class UecebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(UecebankingApplication.class, args);
	}

}
