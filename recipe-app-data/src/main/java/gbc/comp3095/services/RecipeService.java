package gbc.comp3095.services;

import gbc.comp3095.models.Recipe;
import gbc.comp3095.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    //*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: service class to handle business logic for recipe and implement the abstract methods declared or inherited in the Recipe Repository interface
// *********************************************************************************//
    private final RecipeRepository recipeRepository;
    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // create, update, delete, find, findAll
    public Recipe create(Recipe object) {
        return recipeRepository.save(object);
    }

    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public Recipe save(Recipe object) {
        return recipeRepository.save(object);
    }

    public void delete(Recipe object) {
        recipeRepository.delete(object);
    }

    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    public Iterable<Recipe> findAll() {
        return recipeRepository.findAll();
    }
}