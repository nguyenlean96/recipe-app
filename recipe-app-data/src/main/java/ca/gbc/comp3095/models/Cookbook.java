package ca.gbc.comp3095.models;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="cookbooks")
public class Cookbook {
    // each cookbook has a user_id, and many recipes that belong to that user's cookbook. (saved in the cookbook_recipe table)
    @Id
    @SequenceGenerator(name="cookbook_generator", sequenceName="cookbook_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // each cookbook has a user
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    String name;
    String description;

    // each cookbook has many recipes
    @ManyToMany()
    @JoinTable(name = "cookbook_recipe", joinColumns = @JoinColumn( name = "user_id"),  inverseJoinColumns = @JoinColumn( name = "recipe_id"))
    private Set<Recipe> recipes  = new HashSet<>();

    public Cookbook() {
    }

    public Cookbook(Long id, User user, Set<Recipe> recipes) {
        this.id = id;
        this.user = user;
        this.recipes = recipes;
    }

    public Cookbook(User user, Set<Recipe> recipes) {
        this.user = user;
        this.recipes = recipes;
    }

    // constructor with no recipes - for the users first cookbook after registration
    public Cookbook(User user, String name, String description) {
        this.user = user;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Cookbook{" +
                "id=" + id +
                ", user=" + user +
                ", recipes=" + recipes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cookbook cookbook = (Cookbook) o;
        return Objects.equals(id, cookbook.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // add helper methods to add and remove recipes from the cookbook
    public void addRecipe(Recipe recipe){
        this.recipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe){
        this.recipes.remove(recipe);
    }
}
