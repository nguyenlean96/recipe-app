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
    public EventPlanService events;
    public IngredientService ingredients;

    public DbContext(UserService users, RecipeService recipes, MealplanService mealplans,  EventPlanService eventPlanService, IngredientService ingredients) {
        this.users = users;
        this.recipes = recipes;
        this.mealplans = mealplans;
        this.events = eventPlanService;
        this.ingredients = ingredients;
    }
}
