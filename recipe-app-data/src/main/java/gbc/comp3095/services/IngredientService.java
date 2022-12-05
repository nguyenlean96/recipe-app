package gbc.comp3095.services;

import gbc.comp3095.models.Ingredient;
import gbc.comp3095.repositories.IngredientRepository;
import gbc.comp3095.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    //*********************************************************************************
    //* Project: Your Recipe App
    //* Assignment: assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588 - 101292266 - 101303158 - 101311327
    //* Date: 2022-11-28
    //* Description: service class to handle business logic for ingredient
    // *********************************************************************************//

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public IngredientService(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    // create, update, delete, find, findAll
    public Ingredient create(Ingredient object) {
        return ingredientRepository.save(object);
    }

    public Ingredient findById(Long id) {
        List<Ingredient> saved_ones = this.ingredientRepository.findAll();
        for (Ingredient i : saved_ones) {
            if (i.getId() == id) return i;
        }
        return null;
    }

    public Ingredient save(Ingredient object) {
        if (object != null) {
            List<Ingredient> saved_ones = this.ingredientRepository.findAll();
            try {
                for (Ingredient i : saved_ones) {
                    if ((i.getDescription() == object.getDescription()) && (i.getQuantity() == object.getQuantity()) && (i.getUnitOfMeasurement() == object.getUnitOfMeasurement()) && (i.getRecipe().getId() == object.getRecipe().getId()))
                        throw new Exception("Duplicates found.");
                }
                return ingredientRepository.save(object);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public void delete(Ingredient object) {
        ingredientRepository.delete(object);
    }

    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    public Iterable<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
}
