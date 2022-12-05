package gbc.comp3095.recipeapp.config;

import gbc.comp3095.services.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DbContext {
    //*******************************************************************************
    //* Project: Your Recipe App
    //* Assignment: Assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588 - 101292266 - 101303158 - 101311327
    //* Date: 2022-10-23
    //* Description: This class is to gather all of the services this application needs
    //* to use in ONE place, all of them are exposed [public]ly to ease the development
    // ******************************************************************************//
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
