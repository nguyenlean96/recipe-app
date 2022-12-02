package gbc.comp3095.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
@Table(name="recipes")
public class Recipe {
//*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: in addition to the recipe attributes, recipe has a many-to-many relationship with users that is stored in the user_recipe table (recipes saved into cookbook).
//* each recipe is created by one user. this is a many-to-one relationship with user.
//* each recipe also can belong to many mealplan items (meals) - that is why the recipe id is stored in the mealplan table as a foreign key
//* ADDING ONE-TO-MANY RELATIONSHIP WITH INGREDIENT FOR ASSIGNMENT 2
// *********************************************************************************//

    // ATTRIBUTES
    @Id
    @SequenceGenerator(name="recipe_generator", sequenceName="recipe_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="recipe_id")
    private Long id;
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
    private String directions;
    private String difficulty; // enum Difficulty - EASY, MODERATE, HARD


    // RELATIONSHIPS
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User creator;

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Mealplan> mealplans = new HashSet<>();

    @ManyToMany(
            mappedBy = "favourite_recipes"
    )
    private Set<User> recipeFavourite = new HashSet<>();

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Ingredient> recipeIngredients = new HashSet<>();

    @ManyToMany(
            mappedBy = "eventRecipes"
    )
    private Set<EventPlan> recipeEvents = new HashSet<>();

    // CONSTRUCTORS
    public Recipe() {
    }

    public Recipe(String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String directions, String difficulty) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.directions = directions;
        this.difficulty = difficulty;
    }

    public Recipe(String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String directions, String difficulty, User creator) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.directions = directions;
        this.difficulty = difficulty;
        this.creator = creator;
    }

    public Recipe(Long id, String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String directions, String difficulty) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.directions = directions;
        this.difficulty = difficulty;
    }

    public Recipe(Long id, String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String directions, String difficulty, User creator) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.directions = directions;
        this.difficulty = difficulty;
        this.creator = creator;
    }

    public Recipe(Long id, String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String directions, String difficulty, User creator, Set<Mealplan> mealplans, Set<User> recipeFavourite, Set<Ingredient> recipeIngredients, Set<EventPlan> recipeEvents) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.directions = directions;
        this.difficulty = difficulty;
        this.creator = creator;
        this.mealplans = mealplans;
        this.recipeFavourite = recipeFavourite;
        this.recipeIngredients = recipeIngredients;
        this.recipeEvents = recipeEvents;
    }

    // GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Set<Ingredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
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
        return creator;
    }

    public void setUser(User user) {
        this.creator = user;
    }

    public Set<Mealplan> getMealplans() {
        return mealplans;
    }

    public void setMealplans(Set<Mealplan> mealplans) {
        this.mealplans = mealplans;
    }

    public Set<User> getUserFavourite() {
        return recipeFavourite;
    }

    public void setUserFavourite(Set<User> recipeFavourite) {
        this.recipeFavourite = recipeFavourite;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<EventPlan> getRecipeEvents() {
        return recipeEvents;
    }

    public void setRecipeEvents(Set<EventPlan> recipeEvents) {
        this.recipeEvents = recipeEvents;
    }

    // EQUALS AND HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // TO STRING


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
                ", directions='" + directions + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }

    // HELPER METHODS
    public void addMealplan(Mealplan mealplan) {
        this.mealplans.add(mealplan);
    }

    public void removeMealplan(Mealplan mealplan) {
        this.mealplans.remove(mealplan);
    }

    public void addUserFavourite(User user) {
        this.recipeFavourite.add(user);
    }

    public void removeUserFavourite(User user) {
        this.recipeFavourite.remove(user);
    }
    public void addIngredient(Ingredient i) {
        this.recipeIngredients.add(i);
    }
    public void removeIngredient(Long id) {
        for (Ingredient i : this.recipeIngredients) {
            if (i.getId() == id)
                this.recipeIngredients.remove(i);
        }
    }
}