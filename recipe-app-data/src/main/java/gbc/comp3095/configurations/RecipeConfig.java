package gbc.comp3095.configurations;

import gbc.comp3095.models.Recipe;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import gbc.comp3095.repositories.RecipeRepository;

@Configuration
public class RecipeConfig {
    //*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: configuration class to load some recipe data into the database as application is started up
// *********************************************************************************//

    @Bean
    CommandLineRunner recipeCommandLineRunner(RecipeRepository recipeRepository) {
        return args -> {
            Recipe recipe1 = new Recipe();
            recipe1.setId(1L);
            recipe1.setName("Quick and Easy Pancit");
            recipe1.setImageUrl("https://i.ibb.co/HzBJdCV/Screen-Shot-2022-10-21-at-10-43-30-PM.png");
            recipe1.setDescription("This is a quick and easy pancit recipe that you can make in less than 30 minutes.");
            recipe1.setPrepTime(10);
            recipe1.setCookTime(20);
            recipe1.setServings(4);
//            recipe1.setIngredients("""
//                1 (12 ounce) package dried rice noodles
//
//                1 teaspoon vegetable oil
//
//                1 onion, finely diced
//
//                3 cloves garlic, minced
//
//                2 cups diced cooked chicken breast meat
//
//                1 small head cabbage, thinly sliced
//
//                4 carrot, thinly sliced
//
//                ¼ cup soy sauce
//
//                2 lemons - cut into wedges, for garnish
//            """);
            recipe1.setDirections("""
                Place rice noodles in a large bowl; cover with warm water and let soften for 8 to 10 minutes. Drain and set aside.

                Meanwhile, heat oil in a wok or large skillet over medium-low heat. Add onion and garlic; cook and stir until onion is tender, about 3 to 5 minutes. Stir in chicken, cabbage, carrots, and soy sauce. Cook until cabbage begins to soften. Toss in noodles and cook, stirring constantly, until heated through.

                Transfer pancit to a serving dish and garnish with lemon wedges.
            """);
            recipe1.setDifficulty("Easy");
            recipeRepository.save(recipe1);
            ////////////////////////////
            Recipe recipe2 = new Recipe();
            recipe2.setId(2L);
            recipe2.setName("Chicken Parmesan");
            recipe2.setImageUrl("https://i.ibb.co/N6y0RF2/Screen-Shot-2022-10-21-at-10-51-03-PM.png");
            recipe2.setDescription("This is a classic chicken parmesan recipe that you can make at home.");
            recipe2.setPrepTime(10);
            recipe2.setCookTime(30);
            recipe2.setServings(4);
//            recipe2.setIngredients("""
//                4 boneless, skinless chicken breast halves
//
//                1 cup all-purpose flour
//
//                2 eggs, beaten
//
//                1 cup Italian seasoned bread crumbs
//
//                1 (24 ounce) jar spaghetti sauce
//
//                1 cup shredded mozzarella cheese
//
//                ¼ cup grated Parmesan cheese
//            """);
            recipe2.setDirections("""
                Preheat oven to 350 degrees F (175 degrees C).

                Place chicken breasts between two sheets of plastic wrap and pound to 1/4-inch thickness.

                Place flour in a shallow dish. Place eggs in a separate shallow dish. Place bread crumbs in a third shallow dish.

                Dip chicken in flour, then in egg, and finally in bread crumbs.

                Place chicken in a 9x13-inch baking dish. Pour spaghetti sauce over chicken. Sprinkle with mozzarella cheese and Parmesan cheese.

                Bake in the preheated oven until chicken is no longer pink in the center and the juices run clear, 30 to 35 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C).
            """);
            recipe2.setDifficulty("Medium");
            recipeRepository.save(recipe2);
            ////////////////////////////
            Recipe recipe3 = new Recipe();
            recipe3.setId(3L);
            recipe3.setName("Chicken and Dumplings");
            recipe3.setImageUrl("https://i.ibb.co/t8TJbpn/Screen-Shot-2022-10-21-at-11-01-03-PM.png");
            recipe3.setDescription("This is a classic chicken and dumplings recipe that you can make at home.");
            recipe3.setPrepTime(10);
            recipe3.setCookTime(30);
            recipe3.setServings(4);
//            recipe3.setIngredients("""
//                1 (3 pound) whole chicken
//
//                1 onion, chopped
//
//                1 (10.75 ounce) can condensed cream of chicken soup
//
//                1 (10.75 ounce) can condensed cream of celery soup
//
//                1 (10.75 ounce) can condensed cream of mushroom soup
//
//                1 (10.75 ounce) can condensed cream of chicken soup
//
//                1 (10.75 ounce) can condensed cream of celery soup
//
//                1 (10.75 ounce) can condensed cream of mushroom soup
//
//                1 (10.75 ounce) can condensed cream of chicken soup
//
//                1 (10.75 ounce) can condensed cream of celery soup
//
//                1 (10.75 ounce) can condensed cream of mushroom soup
//
//                1 (10.75 ounce) can condensed cream of chicken soup
//
//                1 (10.75 ounce) can condensed cream of celery soup
//
//                1 (10.75 ounce) can condensed cream of mushroom soup
//
//                1 (10.75 ounce) can condensed cream of chicken soup
//
//                1 (10.75 ounce) can condensed cream of celery soup
//
//                1 (10.75 ounce) can condensed cream of mushroom soup
//
//                1 (10.75 ounce) can condensed cream of chicken soup
//
//                1 (10.75 ounce) can condensed cream of celery soup
//
//                1 (10.75 ounce) can condensed cream of mushroom soup
//
//                1 (10.75 ounce) can condensed cream of chicken soup
//
//                1 (10.75 ounce) can condensed cream of celery soup
//
//                1 (10.75 ounce) can condensed cream of mushroom soup
//
//                1 (10.75 ounce) can condensed cream of chicken soup
//
//                1 (10.75 ounce) can condensed cream of celery soup
//
//                1 (10.75 ounce) can condensed cream of mushroom soup
//
//                1 (10.75 ounce) can condensed cream of chicken soup
//
//                1 (10.75 ounce) can condensed cream of celery soup
//
//                1 (10.75 ounce) can condensed cream of mushroom soup
//
//                1 (10.75 ounce) can condensed cream of chicken soup""");
//            recipe3.setDirections("""
//                Preheat oven to 350 degrees F (175 degrees C).
//
//                Place chicken breasts between two sheets of plastic wrap and pound to 1/4-inch thickness.
//
//                Place flour in a shallow dish. Place eggs in a separate shallow dish. Place bread crumbs in a third shallow dish.
//
//                Dip chicken in flour, then in egg, and finally in bread crumbs.
//
//                Place chicken in a 9x13-inch baking dish. Pour spaghetti sauce over chicken. Sprinkle with mozzarella cheese and Parmesan cheese.
//
//                Bake in the preheated oven until chicken is no longer pink in the center and the juices run clear, 30 to 35 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C).
//            """);
            recipe3.setDifficulty("Hard");
            // these are the users the recipe is saved or created by
            recipeRepository.save(recipe3);
            ////////////////////////////
        };
    }

}