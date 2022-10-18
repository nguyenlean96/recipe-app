package ca.gbc.comp3095;

import ca.gbc.comp3095.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(RecipeAppApplication.class, args);
		System.out.println("Application started...");
		//		test
		//		System.out.println(
		//				new User("jdoe",
		//						"password",
		//						"j.doe@gmail.com",
		//						"john",
		//						"doe",
		//						"123-456-7890")
		//		);


	}

}
