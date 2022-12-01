package gbc.comp3095.repositories;

import gbc.comp3095.models.EventPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPlanRepository extends JpaRepository<EventPlan, Long> {
}
