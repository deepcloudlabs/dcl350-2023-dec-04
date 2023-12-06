package com.example.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrMicroserviceApplication {

	public static void main(String[] args) {
		var lang = System.getProperty("user.language");
		System.err.println(lang);
		SpringApplication.run(HrMicroserviceApplication.class, args);
	}

}
