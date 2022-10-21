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
        User user02 = new User(
                "annl96",
                "abc@123",
                "annl96@mail.com",
                "Le An",
                "Nguyen",
                "437-324-0001"
        );
        User user03 = new User(
                "Sarah Sami",
                "sarahs@123",
                "sarahs@mail.com",
                "Sarah",
                "Sami",
                "647-401-2001"
        );
        userRepository.save(user01);
        userRepository.save(user02);
        userRepository.save(user03);

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
        Recipe recipe02 = new Recipe(
                "Fluffy Cake Doughnuts",
                "/images/mDishes/10-miniburgers-fa7ec304b1c146ba9dc84e452c288e1d.webp",
                "This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes.This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes.This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes.This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes.This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes.",
                20, 10, 12,
                "2 cups all-purpose flour\n" +
                        "\n" +
                        "¾ cup white sugar\n" +
                        "\n" +
                        "2 teaspoons baking powder\n" +
                        "\n" +
                        "¼ teaspoon ground nutmeg\n" +
                        "\n" +
                        "¼ teaspoon ground cinnamon\n" +
                        "\n" +
                        "1 teaspoon salt\n" +
                        "\n" +
                        "¾ cup milk\n" +
                        "\n" +
                        "2 eggs, beaten\n" +
                        "\n" +
                        "1 teaspoon vanilla extract\n" +
                        "\n" +
                        "1 tablespoon shortening\n" +
                        "\n" +
                        "1 cup confectioners' sugar\n" +
                        "\n" +
                        "2 tablespoons hot water\n" +
                        "\n" +
                        "½ teaspoon almond extract",
                " Preheat oven to 325 degrees F (165 degrees C). Lightly grease a doughnut pan.\n" +
                        "\n" +
                        "Mix flour, sugar, baking powder, nutmeg, cinnamon and salt. Stir in milk, eggs, vanilla and shortening. Beat together until well blended.\n" +
                        "\n" +
                        "Fill each doughnut cup approximately 3/4 full. Bake 8 to 10 minutes in the preheated oven, until doughnuts spring back when touched. Allow to cool slightly before removing from pan.\n" +
                        "\n" +
                        "To make glaze, blend confectioners' sugar, hot water and almond extract in a small bowl. Dip doughnuts in the glaze when serving.",
                "EASY"
        );
        Recipe recipe03 = new Recipe(
                "Baked Apple Cider Donuts",
                "/images/mDishes/4170561-pork-chops-with-apple-cider-glaze-photo-by-allrecipes-magazine-66aa6bf252e6495a8676478d7ec84107.webp",
                "All the world's great donuts are fried, except there are a few rare examples of when they're not—and this incredibly delicious and easy-to-make apple cider donut is one notable exception.",
                15, 20, 12,
                "2 cups all-purpose flour\n" +
                        "\n" +
                        "¾ teaspoon baking powder\n" +
                        "\n" +
                        "¾ teaspoon baking soda\n" +
                        "\n" +
                        "¼ teaspoon fine salt\n" +
                        "\n" +
                        "1 teaspoon ground cinnamon\n" +
                        "\n" +
                        "1 pinch ground cardamom\n" +
                        "\n" +
                        "1 pinch freshly grated nutmeg\n" +
                        "\n" +
                        "½ cup white sugar\n" +
                        "\n" +
                        "½ cup packed brown sugar\n" +
                        "\n" +
                        "½ cup warm milk\n" +
                        "\n" +
                        "6 tablespoons unsalted butter, melted, divided\n" +
                        "\n" +
                        "¾ teaspoon vanilla extract\n" +
                        "\n" +
                        "1 large egg",
                " Preheat the oven to 375 degrees F (190 degrees C). Butter two 6-cup donut pans.\n" +
                        "\n" +
                        "Pour apple cider into a saucepan and place over medium heat. Bring to a simmer and let it cook, watching carefully, until the cider is reduced to 1/2 cup. If it reduces too much, add enough water to make 1/2 cup. Set aside until needed.\n" +
                        "\n" +
                        "Add flour, baking powder, baking soda, salt, 1 teaspoon cinnamon, cardamom, and nutmeg to a large bowl. Mix with a whisk until combined and set aside until needed.\n" +
                        "\n" +

                        "If desired, while still slightly warm, brush the donuts lightly with remaining melted butter. Mix 1 cup white sugar and 1 tablespoon cinnamon together for topping in a shallow dish; toss in donuts to coat. Let cool completely before serving. ",
                "MODERATE"
        );
        /*
        Recipe recipe05 = new Recipe(
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
        recipeRepository.save(recipe02);
        recipeRepository.save(recipe03);

        System.out.println("Entities have been initialized successfully...");
    }
}
