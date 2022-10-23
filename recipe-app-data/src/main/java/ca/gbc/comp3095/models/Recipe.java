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

    // ATTRIBUTES
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
    @OneToMany(mappedBy = "recipe")
    private Set<Mealplan> mealplans = new HashSet<>();

    @ManyToMany(mappedBy = "recipes")
    private Set<User> users;

    // constructors
    public Recipe() {
    }

    // with all attributes including id and relationships
    public Recipe(long id, String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty, Set<Mealplan> mealplans, Set<User> users) {
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
        this.mealplans = mealplans;
        this.users = users;
    }

    // without id
    public Recipe(String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty, Set<Mealplan> mealplans, Set<User> users) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
        this.mealplans = mealplans;
        this.users = users;
    }

    // without id and relationships
    public Recipe(String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty) {
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

    // with users - without mealplans
    public Recipe(String name, String imageUrl, String description, Integer prepTime, Integer cookTime, Integer servings, String ingredients, String directions, String difficulty, Set<User> users) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.difficulty = difficulty;
        this.users = users;
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

    public Set<Mealplan> getMealplans() {
        return mealplans;
    }

    public void setMealplans(Set<Mealplan> mealplans) {
        this.mealplans = mealplans;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // equals and hashcode
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

    // toString
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
                ", mealplans=" + mealplans +
                ", users=" + users +
                '}';
    }

    // helper methods for relationships
    public void addMealplan(Mealplan mealplan) {
        mealplans.add(mealplan);
        mealplan.setRecipe(this);
    }

    public void removeMealplan(Mealplan mealplan) {
        mealplans.remove(mealplan);
        mealplan.setRecipe(null);
    }

    public void addUser(User user) {
        users.add(user);
        user.getRecipes().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getRecipes().remove(this);
    }
}
