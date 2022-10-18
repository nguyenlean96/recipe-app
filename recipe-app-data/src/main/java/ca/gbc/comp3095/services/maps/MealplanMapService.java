package ca.gbc.comp3095.services.maps;

import ca.gbc.comp3095.models.Mealplan;
import ca.gbc.comp3095.services.MealplanService;
import org.springframework.stereotype.Component;

import java.util.Set;

public class MealplanMapService extends AbstractMapService<Mealplan, Long> implements MealplanService {

    @Override
    public Set<Mealplan> findAll() {
        return super.findAll();
    }

    @Override
    public Mealplan findById(Long id) {
        return super.findById(id);
    }

    public Mealplan save(Mealplan object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Mealplan object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
