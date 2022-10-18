package ca.gbc.comp3095.services;

import ca.gbc.comp3095.models.Recipe;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService extends CrudService<Recipe, Long> {

}
