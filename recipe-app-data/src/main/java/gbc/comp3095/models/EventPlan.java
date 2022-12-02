package gbc.comp3095.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="eventplan")
public class EventPlan {
    //*********************************************************************************
    //* Project: Your Recipe App
    //* Assignment: assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588 - 101292266 - 101303158 - 101311327
    //* Date: 2022-11-28
    //* Description: the ingredient class is used to store the ingredients for each recipe
    //* each ingredient has a description, a quantity, and a unit of measurement (unit of measurement is enum)
    //* the relationship between recipe and ingredient is one-to-many and many-to-one
    //* the relationship between shopping list and ingredient is many-to-many
    // *********************************************************************************//
    // ATTRIBUTES
    @Id
    @SequenceGenerator(name="recipe_generator", sequenceName="recipe_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;
    private String dish;

    private String date;

    @Enumerated(EnumType.STRING)
    private EventType eventType; // nullable and null by default (shown to user as "none" pre-selected)

    // RELATIONSHIPS
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User eventUser;

    @ManyToMany
    @JoinTable(
            name="eventplan_recipe",
            joinColumns = @JoinColumn(name="eventplan_id"),
            inverseJoinColumns = @JoinColumn(name="recipe_id")
    )
    private Set<Recipe> eventRecipes = new HashSet<>();

    public EventPlan() {

    }

    public EventPlan(String description, String dish, String date, EventType eventType) {
        this.description = description;
        this.dish = dish;
        this.date = date;
        this.eventType = eventType;
    }

    public EventPlan(String description, String dish, String date, EventType eventType, User eventUser) {
        this.description = description;
        this.dish = dish;
        this.date = date;
        this.eventType = eventType;
        this.eventUser = eventUser;
    }

    public EventPlan(Long id, String description,  String dish, String date, EventType eventType, User eventUser) {
        this.id = id;
        this.description = description;
        this.dish = dish;
        this.date = date;
        this.eventType = eventType;
        this.eventUser = eventUser;
    }

    public EventPlan(Long id, String description,  String dish, String date, EventType eventType, User eventUser, Set<Recipe> eventRecipes) {
        this.id = id;
        this.description = description;
        this.dish = dish;
        this.date = date;
        this.eventType = eventType;
        this.eventUser = eventUser;
        this.eventRecipes = eventRecipes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public User getEventUser() {
        return eventUser;
    }

    public void setEventUser(User eventUser) {
        this.eventUser = eventUser;
    }

    public Set<Recipe> getEventRecipes() {
        return eventRecipes;
    }

    public void setEventRecipes(Set<Recipe> eventRecipes) {
        this.eventRecipes = eventRecipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventPlan eventPlan)) return false;
        return Objects.equals(getDish(), eventPlan.getDish()) && Objects.equals(getDate(), eventPlan.getDate()) && getEventType() == eventPlan.getEventType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDish(), getDate(), getEventType());
    }

    @Override
    public String toString() {
        return "EventPlan{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dish='" + dish + '\'' +
                ", date='" + date + '\'' +
                ", eventType=" + eventType +
                '}';
    }

    public void addEventRecipe(Recipe r) { this.eventRecipes.add(r); }
    public void removeEventRecipe(Recipe r) { this.eventRecipes.remove(r); }

}
