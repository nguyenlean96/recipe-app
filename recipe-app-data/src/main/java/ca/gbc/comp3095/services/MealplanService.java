package ca.gbc.comp3095.services;

import ca.gbc.comp3095.models.Mealplan;
import ca.gbc.comp3095.repositories.MealplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealplanService {
    private final MealplanRepository mealplanRepository;
    @Autowired
    public MealplanService(MealplanRepository mealplanRepository) {
        this.mealplanRepository = mealplanRepository;
    }

    // create, update, delete, find, findAll
    public Mealplan create(Mealplan object) {
        return mealplanRepository.save(object);
    }

    public Mealplan findById(Long id) {
        return mealplanRepository.findById(id).orElse(null);
    }

    public Mealplan save(Mealplan object) {
        return mealplanRepository.save(object);
    }

    public void delete(Mealplan object) {
        mealplanRepository.delete(object);
    }

    public void deleteById(Long id) {
        mealplanRepository.deleteById(id);
    }

    public Iterable<Mealplan> findAll() {
        return mealplanRepository.findAll();
    }
}
