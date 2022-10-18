package ca.gbc.comp3095.models;

public class Mealplan {
    long id;
    String dish;
    // foreign key to recipe_id
    long recipe_id;
    // foreign key to user_id
    long user_id;
    String date;

    public Mealplan() {
    }

    public Mealplan(long id, String dish, long recipe_id, long user_id, String date) {
        this.id = id;
        this.dish = dish;
        this.recipe_id = recipe_id;
        this.user_id = user_id;
        this.date = date;
    }

    public Mealplan(String dish, long recipe_id, long user_id, String date) {
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

    public long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
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
}
