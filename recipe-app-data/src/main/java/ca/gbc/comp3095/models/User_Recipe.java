package ca.gbc.comp3095.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="user_recipe")
public class User_Recipe {
    // this is the join table for the many-to-many relationship between users and recipes
    // however a separate entity is needed to add columns to the join table

    // ATTRIBUTES
    @Id
    @SequenceGenerator(name="user_recipe_generator", sequenceName="user_recipe_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private boolean isCreated;

    private boolean isSaved;

    // constructor
    public User_Recipe() {
    }

    // getters and setters
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

    // equals and hashcode
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

    // toString - for testing
    @Override
    public String toString() {
        return "User_Recipe{" +
                "id=" + id +
                ", isCreated=" + isCreated +
                ", isSaved=" + isSaved +
                '}';
    }
}

