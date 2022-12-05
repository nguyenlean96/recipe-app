package gbc.comp3095.recipeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"gbc.comp3095"})
@EntityScan("gbc.comp3095")
@EnableJpaRepositories("gbc.comp3095.repositories")
public class RecipeAppApplication {
	//*******************************************************************************
	//* Project: Your Recipe App
	//* Assignment: Assignment 2
	//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
	//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
	//* Date: 2022-10-23
	//* Description: This main class is used to invoke the application itself, the
	//* @EnableJpaRepositories, @CompnentScan and @EntityScan annotation are added to
	//* aid this application to find the resources
	// ******************************************************************************//
	public static void main(String[] args) {
		SpringApplication.run(RecipeAppApplication.class, args);
	}
}
