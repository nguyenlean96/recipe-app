package ca.gbc.comp3095.services;

import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
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
