package ca.gbc.comp3095.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Mealplan {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    String dish;
    // foreign key to recipe_id -- annotate as @ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Recipe recipe_id;
    // foreign key to user_id -- annotate as @ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user_id;
    String date;

    public Mealplan() {
    }

    public Mealplan(long id, String dish, Recipe recipe_id, User user_id, String date) {
        this.id = id;
        this.dish = dish;
        this.recipe_id = recipe_id;
        this.user_id = user_id;
        this.date = date;
    }

    public Mealplan(String dish, Recipe recipe_id, User user_id, String date) {
        this.dish = dish;
        this.recipe_id = recipe_id;
        this.user_id = user_id;
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

    public Recipe getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Recipe recipe_id) {
        this.recipe_id = recipe_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Mealplan{" +
                "id=" + id +
                ", dish='" + dish + '\'' +
                ", recipe_id=" + recipe_id +
                ", user_id=" + user_id +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mealplan mealplan = (Mealplan) o;
        return id == mealplan.id &&
                Objects.equals(dish, mealplan.dish) &&
                Objects.equals(recipe_id, mealplan.recipe_id) &&
                Objects.equals(user_id, mealplan.user_id) &&
                Objects.equals(date, mealplan.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dish, recipe_id, user_id, date);
    }
}
