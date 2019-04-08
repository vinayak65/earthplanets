package com.example.demo;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.service.PlanetService;
import com.example.config.HibernateInitializator;

@SpringBootApplication
@EntityScan("com.example.domain")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.example.repositories")
@ComponentScan(basePackageClasses={PlanetService.class} ,basePackages = "com.example.webservice.domain,com.example.demo,com.example.webservice.config")

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	/*
	@Bean
	  public EntityManagerFactory entityManagerFactory() {
		     HibernateInitializator hi = new HibernateInitializator();
		     return hi.entityManagerFactory();
	            
	  }
*/
}
