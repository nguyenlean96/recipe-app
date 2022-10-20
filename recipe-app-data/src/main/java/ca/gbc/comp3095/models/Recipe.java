package ca.gbc.comp3095.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
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
    @ManyToMany(mappedBy = "recipes")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<Mealplan> mealplans = new HashSet<>();

    public Recipe() {
    }

    public Recipe(long id, String name, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty, Set<User> users, Set<Mealplan> mealplans) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
        this.users = users;
        this.mealplans = mealplans;
    }

    public Recipe(String name, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty, Set<User> users, Set<Mealplan> mealplans) {
        this.name = name;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
        this.users = users;
        this.mealplans = mealplans;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
                ", description='" + description + '\'' +
                ", prepTime=" + prepTime +
                ", cookTime=" + cookTime +
                ", servings=" + servings +
                ", ingredients='" + ingredients + '\'' +
                ", directions='" + directions + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", users=" + users +
                ", mealplans=" + mealplans +
                '}';
    }

    public void addMealplan(Mealplan mealplan) {
        this.mealplans.add(mealplan);
    }

    public void removeMealplan(Mealplan mealplan) {
        this.mealplans.remove(mealplan);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }
}
