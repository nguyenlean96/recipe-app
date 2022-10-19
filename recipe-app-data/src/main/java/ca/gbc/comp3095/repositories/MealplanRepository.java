package ca.gbc.comp3095.repositories;

import ca.gbc.comp3095.models.Mealplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealplanRepository extends JpaRepository<Mealplan, Long> {
}
