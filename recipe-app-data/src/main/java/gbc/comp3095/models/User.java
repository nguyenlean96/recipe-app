package gbc.comp3095.models;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
//*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: each user has the usual profile attributes, but also has a many-to-many relationship with recipes that is stored in the user_recipe table.
//* the contents of the user_recipe table are the saved recipes (cookbook recipes).
//* the user also has a one-to-many relationship with recipe which is for the recipes that the user has created.
//* each user can have many meal plan items (meals) - that is why the user id is stored in the mealplan table as a foreign key on each row
//* a password encryption method is also added to the user class to encrypt the password before it is stored in the database
// *********************************************************************************//

    // ATTRIBUTES
    @Id
    @SequenceGenerator(name="user_generator", sequenceName="user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE  )
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;


    // RELATIONSHIPS
    @OneToMany(mappedBy = "user")
    private Set<Recipe> created_recipes = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Mealplan> mealplans = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Recipe> cookbook_recipes = new HashSet<>();


    // CONSTRUCTORS
    public User() {
    }

    public User(Long id, String username, String password, String email, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User(Long id, String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Mealplan> mealplans, Set<Recipe> cookbook_recipes) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mealplans = mealplans;
        this.cookbook_recipes = cookbook_recipes;
    }

    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Recipe> cookbook_recipes) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.cookbook_recipes = cookbook_recipes;
    }

    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Recipe> created_recipes, Set<Recipe> cookbook_recipes) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.created_recipes = created_recipes;
        this.cookbook_recipes = cookbook_recipes;
    }

    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Recipe> created_recipes, Set<Mealplan> mealplans, Set<Recipe> cookbook_recipes) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.created_recipes = created_recipes;
        this.mealplans = mealplans;
        this.cookbook_recipes = cookbook_recipes;
    }

    // GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Recipe> getCreated_recipes() {
        return created_recipes;
    }

    public void setCreated_recipes(Set<Recipe> created_recipes) {
        this.created_recipes = created_recipes;
    }

    public Set<Mealplan> getMealplans() {
        return mealplans;
    }

    public void setMealplans(Set<Mealplan> mealplans) {
        this.mealplans = mealplans;
    }

    public Set<Recipe> getCookbook_recipes() {
        return cookbook_recipes;
    }

    public void setCookbook_recipes(Set<Recipe> cookbook_recipes) {
        this.cookbook_recipes = cookbook_recipes;
    }

    // METHODS
    // ENCRYPT USER PASSWORD
    public void encryptPassword() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }

    // CHECK USER PASSWORD
    public boolean isMatched(String password) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        return b.matches(password, this.password);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.firstName);
        hash = 97 * hash + Objects.hashCode(this.lastName);
        hash = 97 * hash + Objects.hashCode(this.phoneNumber);
        hash = 97 * hash + Objects.hashCode(this.created_recipes);
        hash = 97 * hash + Objects.hashCode(this.mealplans);
        hash = 97 * hash + Objects.hashCode(this.cookbook_recipes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.created_recipes, other.created_recipes)) {
            return false;
        }
        if (!Objects.equals(this.mealplans, other.mealplans)) {
            return false;
        }
        if (!Objects.equals(this.cookbook_recipes, other.cookbook_recipes)) {
            return false;
        }
        return true;
    }

    // HELPER METHODS
    public void addRecipeToCookbook(Recipe recipe) {
        this.cookbook_recipes.add(recipe);
    }

    public void removeRecipeFromCookbook(Recipe recipe) {
        this.cookbook_recipes.remove(recipe);
    }

    public void addRecipeToCreatedRecipes(Recipe recipe) {
        this.created_recipes.add(recipe);
    }

    public void removeRecipeFromCreatedRecipes(Recipe recipe) {
        this.created_recipes.remove(recipe);
    }

    public void addMealplan(Mealplan mealplan) {
        this.mealplans.add(mealplan);
    }

    public void removeMealplan(Mealplan mealplan) {
        this.mealplans.remove(mealplan);
    }
}