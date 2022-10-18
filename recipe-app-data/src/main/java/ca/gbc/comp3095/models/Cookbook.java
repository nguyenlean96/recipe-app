package ca.gbc.comp3095.models;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

public class Cookbook{
    private long id;
    // foreign key to user_id
    private long user_id;
    private String name;

    public Cookbook() {
    }
    public Cookbook(long id, long user_id, String name) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
    }

    public Cookbook(long user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // RELATIONSHIPS
    // one to many relationship with recipe

}
