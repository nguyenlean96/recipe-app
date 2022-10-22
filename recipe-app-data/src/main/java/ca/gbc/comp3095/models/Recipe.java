package ca.gbc.comp3095.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="recipes")
public class Recipe {
    // each recipe has the usual recipe attributes
    // each recipe can be created by one user
    @Id
    @SequenceGenerator(name="recipe_generator", sequenceName="recipe_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String imageUrl;
    @Lob
    @Column(length=512)
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    @Lob
    @Column(length=512)
    private String ingredients;
    @Lob
    @Column(length=512)
    private String directions;
    private String difficulty; // enum Difficulty - EASY, MODERATE, HARD

    // RELATIONSHIPS
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "recipes")
    private Set<Cookbook> cookbooks = new HashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<Mealplan> mealplans = new HashSet<>();

    public Recipe() {
    }

    public Recipe(long id, String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty, User user, Set<Cookbook> cookbooks, Set<Mealplan> mealplans) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
        this.user = user;
        this.cookbooks = cookbooks;
        this.mealplans = mealplans;
    }

    public Recipe(String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty, User user, Set<Cookbook> cookbooks, Set<Mealplan> mealplans) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
        this.user = user;
        this.cookbooks = cookbooks;
        this.mealplans = mealplans;
    }
    // without mealplans
    public Recipe (String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty, User user, Set<Cookbook> cookbooks) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
        this.user = user;
        this.cookbooks = cookbooks;
    }

    // without cookbooks and mealplans
    public Recipe (String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty, User user) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
        this.user = user;
    }

    // without cookbooks, mealplans, and user
    public Recipe (String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
    }

    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Cookbook> getCookbooks() {
        return cookbooks;
    }

    public void setCookbooks(Set<Cookbook> cookbooks) {
        this.cookbooks = cookbooks;
    }

    public Set<Mealplan> getMealplans() {
        return mealplans;
    }

    public void setMealplans(Set<Mealplan> mealplans) {
        this.mealplans = mealplans;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", prepTime=" + prepTime +
                ", cookTime=" + cookTime +
                ", servings=" + servings +
                ", ingredients='" + ingredients + '\'' +
                ", directions='" + directions + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", user=" + user +
                ", cookbooks=" + cookbooks +
                ", mealplans=" + mealplans +
                '}';
    }


}
