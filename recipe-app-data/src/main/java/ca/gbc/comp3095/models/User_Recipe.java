package ca.gbc.comp3095.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="user_recipe")
public class User_Recipe {
//*********************************************************************************
//* Project: The Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: this is the join table for the many-to-many relationship between users and recipes
//* two new attributes are added to the join table: isCreated and isSaved
//* this is to make the distinction between the recipes that the user created (my recipes) and the recipes that the user saved (my cookbook)
// *********************************************************************************//

    // ATTRIBUTES
    @Id
    @SequenceGenerator(name="user_recipe_generator", sequenceName="user_recipe_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private boolean isCreated;
    private boolean isSaved;

    // DEFAULT CONSTRUCTOR
    public User_Recipe() {
    }

    // GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    // METHODS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User_Recipe that = (User_Recipe) o;
        return isCreated == that.isCreated &&
                isSaved == that.isSaved &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isCreated, isSaved);
    }

    @Override
    public String toString() {
        return "User_Recipe{" +
                "id=" + id +
                ", isCreated=" + isCreated +
                ", isSaved=" + isSaved +
                '}';
    }
}

