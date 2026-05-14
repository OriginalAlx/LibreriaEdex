package com.libreria.edex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.libreria.edex") 
public class MainView {

	public static void main(String[] args) {
		SpringApplication.run(MainView.class, args);
	}

}

