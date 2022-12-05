package gbc.comp3095.services;

import gbc.comp3095.models.EventPlan;
import gbc.comp3095.models.Mealplan;
import gbc.comp3095.repositories.EventPlanRepository;
import org.springframework.stereotype.Service;

@Service
public class EventPlanService {
    //*********************************************************************************
    //* Project: Your Recipe App
    //* Assignment: assignment 1
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588 - 101292266 - 101303158 - 101311327
    //* Date: 2022-10-23
    //* Description: EventPlan class to handle CRUD process for EventPlan objects
    // *********************************************************************************//
    private EventPlanRepository eventPlanRepository;

    public EventPlanService(EventPlanRepository eventPlanRepository) {
        this.eventPlanRepository = eventPlanRepository;
    }

    public EventPlan create(EventPlan object) {
        return eventPlanRepository.save(object);
    }

    public EventPlan findById(Long id) {
        return eventPlanRepository.findById(id).orElse(null);
    }

    public EventPlan save(EventPlan object) {
        return eventPlanRepository.save(object);
    }

    public void delete(EventPlan object) {
        eventPlanRepository.delete(object);
    }

    public void deleteById(Long id) {
        eventPlanRepository.deleteById(id);
    }

    public Iterable<EventPlan> findAll() {
        return eventPlanRepository.findAll();
    }
}
