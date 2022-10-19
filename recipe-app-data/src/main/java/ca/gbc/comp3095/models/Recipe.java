package ca.gbc.comp3095.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String ingredients;
    private String directions;
    private String difficulty; // enum Difficulty - EASY, MODERATE, HARD

    // RELATIONSHIPS
    // many to many with user = my recipes (when a user creates a recipe)
    @ManyToMany()
    @JoinTable(name = "user_recipe", joinColumns = @JoinColumn( name = "recipe_id"),  inverseJoinColumns = @JoinColumn( name = "user_id"))
    private Set<User> users  = new HashSet<>();
    // add 2 fields to this pivot table - yn_created_by, yn_favorited_by
    // if this is a user that has created the recipe, yn_created_by = 1 = displayed as "my recipes" on the user's profile
    // if this is a user that has favorited the recipe, yn_favorited_by = 1 = displayed as "user's cookbook" on view

    public Recipe() {
    }

    public Recipe(long id, String name, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty) {
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

    public Recipe(String name, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty) {
        this.name = name;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
    }

    public Recipe(String name, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty, Set<User> users) {
        this.name = name;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prepTime=" + prepTime +
                ", cookTime=" + cookTime +
                ", servings=" + servings +
                ", ingredients='" + ingredients + '\'' +
                ", directions='" + directions + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", users=" + users +
                '}';
    }
}
