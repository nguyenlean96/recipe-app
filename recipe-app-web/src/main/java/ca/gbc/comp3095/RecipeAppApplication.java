package ca.gbc.comp3095;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RecipeAppApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(RecipeAppApplication.class, args);
		System.out.println("Application started...");




	}

}
