package gbc.comp3095.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ShoppingList {
    //*********************************************************************************
    //* Project: Your Recipe App
    //* Assignment: assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588 - 101292266 - 101303158 - 101311327
    //* Date: 2022-11-28
    //* Description: the shopping list for users which has a one-to-one relationship with user
    //* and a many-to-many relationship with ingredient
    // *********************************************************************************//
    @Id
    @SequenceGenerator(name="recipe_generator", sequenceName="recipe_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="shoppinglist_id")
    private long id;

    // RELATIONSHIPS
    @OneToOne(mappedBy = "shoppingList")
    // @MapsId
    private User user;

    @ManyToMany
    @JoinTable(
            name = "shopping_list_ingredient",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients;
}
