package com.anbank.eva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.anbank.eva.config.EvaConfigure;

@SpringBootApplication
@EnableScheduling
public class EvaApplication {

	public static void main(String[] args) {
		// init
		EvaConfigure.initConfigure();
		
		SpringApplication.run(EvaApplication.class, args);
	}
}
