package gbc.comp3095.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="mealplans")
public class Mealplan {
    //*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: the mealplan ITEMS (meals) are stored here for a simpler database
//* each mealplan item has: one user - one recipe - the date they would eat the recipe - and the meal it is for (breakfast, lunch, dinner, snack)
//* ASSIGNMENT 2:
//* Description: the event type enum class is used to store the event type options for each meal plan - if it is left null then it is simply a meal plan
//* However, if an event type is selected then it is an event plan (i.e. a meal plan for a specific event)
// *********************************************************************************//
    // ATTRIBUTES
    @Id
    @SequenceGenerator(name="mealplan_generator", sequenceName="mealplan_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    String dish;
    String date;

    @Enumerated(EnumType.STRING)
    private EventType eventType; // nullable and null by default (shown to user as "none" pre-selected)

    // RELATIONSHIPS
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    // CONSTRUCTORS
    public Mealplan() {
    }

    public Mealplan(long id, String dish, User user, Recipe recipe, String date) {
        this.id = id;
        this.dish = dish;
        this.user = user;
        this.recipe = recipe;
        this.date = date;
    }

    public Mealplan(String dish, User user, Recipe recipe, String date) {
        this.dish = dish;
        this.user = user;
        this.recipe = recipe;
        this.date = date;
    }

    // GETTERS AND SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // METHODS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mealplan mealplan = (Mealplan) o;
        return id == mealplan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Mealplan{" +
                "id=" + id +
                ", dish='" + dish + '\'' +
                ", user=" + user +
                ", recipe=" + recipe +
                ", date='" + date + '\'' +
                '}';
    }

    // HELPER METHODS TO ADD AND REMOVE RECIPES FROM MEALPLAN
    public void addRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void removeRecipe(Recipe recipe) {
        this.recipe = null;
    }

}