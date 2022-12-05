package gbc.comp3095.recipeapp.bootstrap;

import gbc.comp3095.models.Ingredient;
import gbc.comp3095.models.Recipe;
import gbc.comp3095.models.UnitOfMeasurement;
import gbc.comp3095.models.User;
import gbc.comp3095.recipeapp.config.DbContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInit {
    //*******************************************************************************
    //* Project: Your Recipe App
    //* Assignment: Assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588 - 101292266 - 101303158 - 101311327
    //* Date: 2022-10-23
    //* Description: configuration class to initialize some user and recipe data into
    //*             the database as application is started up
    // ******************************************************************************//
    private DbContext context;
    public DataInit(DbContext context) {
        this.context = context;
    }

    @Bean
    CommandLineRunner userCommandLineRunner() {
        return args -> {
            UserInit();
            RecipeInit();
        };
    }

    private void UserInit() {
        User user1 = new User("sarah.sami", "iamawesomebutvertired", "sarah7sami@gmail.com",
                "Sarah", "Sami", "437-279-0000");
        user1.encryptPassword();
        this.context.users.save(user1);


        User user2 = new User();
        user2.setUsername("lean.96");
        user2.setPassword(("leanisawesometooeveryoneelsesucks"));
        user2.encryptPassword();
        user2.setEmail("lean.nguyen@yahoo.com");
        user2.setPhoneNumber("647-123-4567");
        user2.setFirstName("lean");
        user2.setLastName("nguyen");
        this.context.users.save(user2);

        User user3 = new User("assignment2", "comp3095", "assignment2@comp3095.gbc.ca",
                "Assignment 2", "Comp3095", "617-000-0001");
        user3.encryptPassword();
        this.context.users.save(user3);
    }

    private void RecipeInit() {
        // Get users first
        User sarah = this.context.users.findByUsername("sarah.sami");
        User lean96 = this.context.users.findByUsername("lean.96");

        Recipe recipe1 = new Recipe();
        // recipe1.setId(1L);
        recipe1.setName("Quick and Easy Pancit");
        recipe1.setImageUrl("https://i.ibb.co/HzBJdCV/Screen-Shot-2022-10-21-at-10-43-30-PM.png");
        recipe1.setDescription("This is a quick and easy pancit recipe that you can make in less than 30 minutes.");
        recipe1.setPrepTime(10);
        recipe1.setCookTime(20);
        recipe1.setServings(4);
        recipe1.setDirections("""
                Place rice noodles in a large bowl; cover with warm water and let soften for 8 to 10 minutes. Drain and set aside.

                Meanwhile, heat oil in a wok or large skillet over medium-low heat. Add onion and garlic; cook and stir until onion is tender, about 3 to 5 minutes. Stir in chicken, cabbage, carrots, and soy sauce. Cook until cabbage begins to soften. Toss in noodles and cook, stirring constantly, until heated through.

                Transfer pancit to a serving dish and garnish with lemon wedges.
            """);
        recipe1.setDifficulty("Easy");
        Recipe saved_recipe1 = this.context.recipes.save(recipe1);

        saved_recipe1.addIngredient(new Ingredient("package dried noodles", 12, UnitOfMeasurement.OUNCE, saved_recipe1));
        saved_recipe1.addIngredient(new Ingredient("vegetable oil", 1, UnitOfMeasurement.TEASPOON, saved_recipe1));
        saved_recipe1.addIngredient(new Ingredient("fined diced", 1, UnitOfMeasurement.UNIT, saved_recipe1));
        saved_recipe1.addIngredient(new Ingredient("minced garlic", 3, UnitOfMeasurement.TEASPOON, saved_recipe1));
        saved_recipe1.addIngredient(new Ingredient("Diced cooked chicken breast meat", 2, UnitOfMeasurement.CUP, saved_recipe1));
        saved_recipe1.addIngredient(new Ingredient("Thin-sliced cabbage", 1, UnitOfMeasurement.UNIT, saved_recipe1));
        saved_recipe1.addIngredient(new Ingredient("Thin-sliced carrot", 4, UnitOfMeasurement.UNIT, saved_recipe1));
        saved_recipe1.addIngredient(new Ingredient("Soy sauce", .75, UnitOfMeasurement.CUP, saved_recipe1));
        saved_recipe1.addIngredient(new Ingredient("Lemon - cut into wedges for garnish", 2, UnitOfMeasurement.UNIT, saved_recipe1));
        saved_recipe1.setCreator(sarah);
        sarah.addRecipeToCreatedRecipes(saved_recipe1);
        this.context.users.save(sarah);


        ////////////////////////////

        Recipe recipe2 = new Recipe();
        // recipe2.setId(2L);
        recipe2.setName("Chicken Parmesan");
        recipe2.setImageUrl("https://i.ibb.co/N6y0RF2/Screen-Shot-2022-10-21-at-10-51-03-PM.png");
        recipe2.setDescription("This is a classic chicken parmesan recipe that you can make at home.");
        recipe2.setPrepTime(10);
        recipe2.setCookTime(30);
        recipe2.setServings(4);
        recipe2.setDirections("""
                Preheat oven to 350 degrees F (175 degrees C).

                Place chicken breasts between two sheets of plastic wrap and pound to 1/4-inch thickness.

                Place flour in a shallow dish. Place eggs in a separate shallow dish. Place bread crumbs in a third shallow dish.

                Dip chicken in flour, then in egg, and finally in bread crumbs.

                Place chicken in a 9x13-inch baking dish. Pour spaghetti sauce over chicken. Sprinkle with mozzarella cheese and Parmesan cheese.

                Bake in the preheated oven until chicken is no longer pink in the center and the juices run clear, 30 to 35 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C).
            """);
        recipe2.setDifficulty("Medium");
        Recipe saved_recipe2 = this.context.recipes.save(recipe2);
        saved_recipe2.addIngredient(new Ingredient("boneless, skinless chicken breast halves", 4, UnitOfMeasurement.UNIT, saved_recipe2));
        saved_recipe2.addIngredient(new Ingredient("all-purpose flour", 1, UnitOfMeasurement.CUP, saved_recipe2));
        saved_recipe2.addIngredient(new Ingredient("eggs, beaten", 2, UnitOfMeasurement.UNIT, saved_recipe2));
        saved_recipe2.addIngredient(new Ingredient("Italian seasoned bread crumbs", 1, UnitOfMeasurement.CUP, saved_recipe2));
        saved_recipe2.addIngredient(new Ingredient("spaghetti sauce", 24, UnitOfMeasurement.OUNCE, saved_recipe2));
        saved_recipe2.addIngredient(new Ingredient("shredded mozzarella cheese", 1, UnitOfMeasurement.CUP, saved_recipe2));
        saved_recipe2.addIngredient(new Ingredient("grated Parmesan cheese", .75, UnitOfMeasurement.CUP, saved_recipe2));
        saved_recipe2.setCreator(lean96);
        lean96.addRecipeToCreatedRecipes(saved_recipe2);
        this.context.users.save(lean96);

        ////////////////////////////

        Recipe recipe3 = new Recipe();
        // recipe3.setId(3L);
        recipe3.setName("Chicken and Dumplings");
        recipe3.setImageUrl("https://i.ibb.co/t8TJbpn/Screen-Shot-2022-10-21-at-11-01-03-PM.png");
        recipe3.setDescription("This is a classic chicken and dumplings recipe that you can make at home.");
        recipe3.setPrepTime(10);
        recipe3.setCookTime(30);
        recipe3.setServings(4);
        recipe3.setDirections("""
                Preheat oven to 350 degrees F (175 degrees C).

                Place chicken breasts between two sheets of plastic wrap and pound to 1/4-inch thickness.

                Place flour in a shallow dish. Place eggs in a separate shallow dish. Place bread crumbs in a third shallow dish.

                Dip chicken in flour, then in egg, and finally in bread crumbs.

                Place chicken in a 9x13-inch baking dish. Pour spaghetti sauce over chicken. Sprinkle with mozzarella cheese and Parmesan cheese.

                Bake in the preheated oven until chicken is no longer pink in the center and the juices run clear, 30 to 35 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C).
            """);
        recipe3.setDifficulty("Hard");
        Recipe saved_recipe3 = this.context.recipes.save(recipe3);
        saved_recipe3.addIngredient(new Ingredient("whole chicken", 3, UnitOfMeasurement.POUND, saved_recipe3));
        saved_recipe3.addIngredient(new Ingredient("onion, chopped", 1, UnitOfMeasurement.UNIT, saved_recipe3));
        saved_recipe3.addIngredient(new Ingredient("can condensed cream of chicken soup", 10.75, UnitOfMeasurement.OUNCE, saved_recipe3));
        saved_recipe3.addIngredient(new Ingredient("can condensed cream of celery soup", 10.75, UnitOfMeasurement.OUNCE, saved_recipe3));
        saved_recipe3.addIngredient(new Ingredient("can condensed cream of mushroom soup", 10.75, UnitOfMeasurement.OUNCE, saved_recipe3));

        // these are the users the recipe is saved or created by
        saved_recipe3.setCreator(lean96);
        lean96.addRecipeToCreatedRecipes(saved_recipe3);
        this.context.users.save(lean96);
        ////////////////////////////

        Recipe recipe4 = new Recipe();
        recipe4.setName("Vegan Pumpkin Pancakes");
        recipe4.setImageUrl("https://www.allrecipes.com/thmb/inDWSTjkIAEuSlrhFifTojGGKnU=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/5602228-cfb872a68685496da2438e68851092b0.jpg");
        recipe4.setDescription("These vegan pancakes take on a hint of fall with the savory goodness of pumpkin, cinnamon, and pecans!");
        recipe4.setPrepTime(15);
        recipe4.setCookTime(10);
        recipe4.setServings(6);
        recipe4.setDirections("""
            Combine almond milk and vinegar together in a measuring cup; set aside for 10 minutes.
            
            Combine water and flaxseed meal together in a bowl; allow to rest until gelatinous, about 5 minutes.
            
            Combine flour, coconut sugar, baking powder, cinnamon, baking soda, and salt in a medium bowl.
            
            Add almond milk mixture, flaxseed meal mixture, pumpkin puree, and coconut oil to the bowl with the flour mixture. Mix until well blended. Fold in pecans.
            
            Heat a lightly oiled griddle over medium-high heat. Drop batter by large spoonfuls onto the griddle and cook until bubbles form and the edges are dry, 3 to 4 minutes. Flip and cook until browned on the other side, 2 to 3 minutes. Repeat with remaining batter.
                """);
        recipe4.setDifficulty("Medium");
        Recipe saved_recipe4 = this.context.recipes.save(recipe4);
        saved_recipe4.addIngredient(new Ingredient("almond milk", 2, UnitOfMeasurement.CUP, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("distilled white vinegar", 2, UnitOfMeasurement.TABLESPOON, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("water", 5, UnitOfMeasurement.TABLESPOON, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("water", 2, UnitOfMeasurement.TABLESPOON, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("all-purpose flour", 2, UnitOfMeasurement.CUP, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("coconut sugar", 2, UnitOfMeasurement.TABLESPOON, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("baking powder", 1, UnitOfMeasurement.TABLESPOON, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("ground cinnamon", 1, UnitOfMeasurement.TABLESPOON, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("baking soda", 2, UnitOfMeasurement.TEASPOON, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("salt", 1, UnitOfMeasurement.TEASPOON, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("pumpkin puree", 1, UnitOfMeasurement.CUP, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("coconut oil", 2, UnitOfMeasurement.TABLESPOON, saved_recipe4));
        saved_recipe4.addIngredient(new Ingredient("toasted chopped pecans", .25, UnitOfMeasurement.CUP, saved_recipe4));

        saved_recipe4.setCreator(sarah);
        sarah.addRecipeToCreatedRecipes(saved_recipe4);
        this.context.users.save(sarah);

    }
}
