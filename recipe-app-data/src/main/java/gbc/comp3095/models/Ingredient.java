package gbc.comp3095.models;

import com.sun.istack.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Ingredient {
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
    private long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String description;

    @NotNull
    @Size(min = 1, max = 6)
    private double quantity;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasurement unitOfMeasurement;

    // RELATIONSHIPS
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToMany(mappedBy = "ingredients")
    private Set<ShoppingList> shoppingLists = new HashSet<>();


    // CONSTRUCTORS
    public Ingredient() {
    }

    public Ingredient(long id, String description, double quantity, UnitOfMeasurement unitOfMeasurement, Recipe recipe) {
            this.id = id;
            this.description = description;
            this.quantity = quantity;
            this.unitOfMeasurement = unitOfMeasurement;
            this.recipe = recipe;
        }

    public Ingredient(String description, double quantity, UnitOfMeasurement unitOfMeasurement, Recipe recipe) {
        this.description = description;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
        this.recipe = recipe;
    }

    // GETTERS AND SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    // EQUALS AND HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id && Double.compare(that.quantity, quantity) == 0 && Objects.equals(description, that.description) && unitOfMeasurement == that.unitOfMeasurement && Objects.equals(recipe, that.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, quantity, unitOfMeasurement, recipe);
    }

    // TO STRING
    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", unitOfMeasurement=" + unitOfMeasurement +
                ", recipe=" + recipe +
                '}';
    }

    // METHODS
    public String getIngredient() {
        return quantity + " " + unitOfMeasurement + " " + description;
    }

    public String getIngredientForEdit() {
        return quantity + " " + unitOfMeasurement + " " + description + " " + recipe.getId();
    }

    public String getIngredientForDelete() {
        return quantity + " " + unitOfMeasurement + " " + description + " " + recipe.getId();
    }

    public String getIngredientForView() {
        return quantity + " " + unitOfMeasurement + " " + description;
    }

    public String getIngredientForViewWithRecipeId() {
        return quantity + " " + unitOfMeasurement + " " + description + " " + recipe.getId();
    }

    public String getIngredientForViewWithRecipeName() {
        return quantity + " " + unitOfMeasurement + " " + description + " " + recipe.getName();
    }

    public String getIngredientForViewWithRecipeNameAndId() {
        return quantity + " " + unitOfMeasurement + " " + description + " " + recipe.getName() + " " + recipe.getId();
    }

    public String getIngredientForViewWithRecipeNameAndIdAndUserId() {
        return quantity + " " + unitOfMeasurement + " " + description + " " + recipe.getName() + " " + recipe.getId() + " " + recipe.getUser().getId();
    }

    public String getIngredientForViewWithRecipeNameAndIdAndUserIdAndUsername() {
        return quantity + " " + unitOfMeasurement + " " + description + " " + recipe.getName() + " " + recipe.getId() + " " + recipe.getUser().getId() + " " + recipe.getUser().getUsername();
    }

    public String getIngredientForViewWithRecipeNameAndIdAndUserIdAndUsernameAndUserEmail() {
        return quantity + " " + unitOfMeasurement + " " + description + " " + recipe.getName() + " " + recipe.getId() + " " + recipe.getUser().getId() + " " + recipe.getUser().getUsername() + " " + recipe.getUser().getEmail();
    }
}