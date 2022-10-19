package ca.gbc.comp3095.models;

import java.util.HashSet;
import java.util.Set;

public class Recipe{
    private long id;
    // created_by - type long id - foreign key to users table
    private long created_by;
    private String name;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String ingredients;
    private String directions;
    private String difficulty; // enum Difficulty - EASY, MODERATE, HARD

    public Recipe() {
    }

    public Recipe(long id, long created_by, String name, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
    }

    // everything but id in constructor for database
    public Recipe(long created_by, String name, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty) {
        this.name = name;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(long created_by) {
        this.created_by = created_by;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", created_by=" + created_by +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prepTime=" + prepTime +
                ", cookTime=" + cookTime +
                ", servings=" + servings +
                ", ingredients='" + ingredients + '\'' +
                ", directions='" + directions + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }

    // RELATIONSHIPS
    // many to one with cookbook
    // many to many with user = my recipes (when a user creates a recipe)
}
