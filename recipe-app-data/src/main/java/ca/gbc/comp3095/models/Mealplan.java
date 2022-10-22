package ca.gbc.comp3095.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="mealplans")
public class Mealplan {
    // the mealplan ITEMS (meals) are stored here for a simpler database
    // each mealplan item has: one user - one recipe - the date they would eat the recipe - and the meal it is for (breakfast, lunch, dinner, snack)
   @Id
   @SequenceGenerator(name="mealplan_generator", sequenceName="mealplan_sequence", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    String dish;
    // foreign key to recipe_id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    // foreign key to user_id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    String date;

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

    // helper methods to add and remove recipes from the mealplan
    public void addRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void removeRecipe(Recipe recipe) {
        this.recipe = null;
    }

}
