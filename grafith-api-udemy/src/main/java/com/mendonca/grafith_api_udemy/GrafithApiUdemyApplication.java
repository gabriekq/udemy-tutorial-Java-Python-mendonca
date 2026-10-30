package com.mendonca.grafith_api_udemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mendonca.controller.PersonController;

@SpringBootApplication
@ComponentScan({"com.mendonca.query","com.mendonca.service","com.mendonca.resolver","com.mendonca.mutation"})
@EntityScan({"com.mendonca.model"})
@ComponentScan(basePackageClasses = {PersonController.class})
@EnableJpaRepositories("com.mendonca.repository")
public class GrafithApiUdemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrafithApiUdemyApplication.class, args);
	}

}
