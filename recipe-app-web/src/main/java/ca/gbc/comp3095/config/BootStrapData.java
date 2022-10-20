package ca.gbc.comp3095.config;

import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.models.User;
import ca.gbc.comp3095.repositories.MealplanRepository;
import ca.gbc.comp3095.repositories.RecipeRepository;
import ca.gbc.comp3095.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BootStrapData  implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final MealplanRepository mealplanRepository;

    public BootStrapData(UserRepository userRepository, RecipeRepository recipeRepository, MealplanRepository mealplanRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.mealplanRepository = mealplanRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create users
        User user01 = new User(
                "username",
                "password@123",
                "username@mail.com",
                "First Name",
                "Last Name",
                "647-400-0001"
        );
        userRepository.save(user01);


        // Create recipes
        Recipe recipe01 = new Recipe(
                "Slow Cooker Beef Stew",
                "/images/mDishes/NicoleMcLaughlintailgatefoodmain-2000-906a3471f6e046c18909f156f877a5f8.webp",
                "This easy slow cooker beef stew recipe made with potatoes, carrots, celery, broth, herbs, and spices is hearty and comforting. You won't be slow to say 'yum'!",
                20, 240, 6,
                "· Stew meat: This recipe starts with two pounds of beef stew meat, cut into 1-inch pieces.\n" +
                        "· Flour: All-purpose flour thickens the broth, ensuring a hearty stew.\n" +
                        "· Seasonings: This beef stew is seasoned with salt, pepper, Worcestershire sauce, paprika, garlic, and a bay leaf.\n" +
                        "· Broth: Use store-bought or homemade beef broth.\n" +
                        "· Vegetables: You'll need carrots, potatoes, onions, and celery.",
                "1. Combine ingredients: Place the beef in the slow cooker, then toss with flour, salt, and pepper. Add the broth, vegetables, Worcestershire sauce, paprika, garlic, and bay leaf. Stir to combine.\n" +
                        "2. Set slow cooker: Cover the Crock-Pot and cook on Low for 8 to 12 hours or on High for 4 to 6 hours.",
                "MODERATE"
        );
        /*
        Recipe recipe02 = new Recipe(
                "",
                "",
                "",
                20, 240, 6,
                "",
                "",
                ""
        );
        Recipe recipe03 = new Recipe(
                "",
                "",
                "",
                20, 240, 6,
                "",
                "",
                ""
        );
        Recipe recipe04 = new Recipe(
                "",
                "",
                "",
                20, 240, 6,
                "",
                "",
                ""
        );Recipe recipe05 = new Recipe(
                "",
                "",
                "",
                20, 240, 6,
                "",
                "",
                ""
        );
        Recipe recipe06 = new Recipe(
                "",
                "",
                "",
                20, 240, 6,
                "",
                "",
                ""
        ); */

        recipeRepository.save(recipe01);

        System.out.println("Entities have been initialized successfully...");
    }
}
