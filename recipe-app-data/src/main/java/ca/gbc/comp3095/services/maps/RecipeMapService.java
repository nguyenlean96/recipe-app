package ca.gbc.comp3095.services.maps;

import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.services.RecipeService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RecipeMapService extends AbstractMapService<Recipe, Long> implements RecipeService {

    @Override
    public Set<Recipe> findAll() {
        return super.findAll();
    }

    @Override
    public Recipe findById(Long id) {
        return super.findById(id);
    }

    public Recipe save(Recipe object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Recipe object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
