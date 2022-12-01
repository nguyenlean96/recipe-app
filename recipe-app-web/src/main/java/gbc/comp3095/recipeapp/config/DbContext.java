package gbc.comp3095.recipeapp.config;

import gbc.comp3095.services.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DbContext {
    public UserService users;
    public RecipeService recipes;
    public MealplanService mealplans;
    public IngredientService ingredients;
    public ShoppingListService shoppinglists;

    public DbContext(UserService users, RecipeService recipes, MealplanService mealplans, IngredientService ingredients, ShoppingListService shoppinglists) {
        this.users = users;
        this.recipes = recipes;
        this.mealplans = mealplans;
        this.ingredients = ingredients;
        this.shoppinglists = shoppinglists;
    }
}
