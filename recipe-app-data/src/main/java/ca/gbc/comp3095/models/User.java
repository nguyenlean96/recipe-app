package ca.gbc.comp3095.models;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
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
//* the recipes that are created by the user are flagged with boolean isCreated in the user_recipe table (displayed as my recipes)
//* and the ones that are just saved are flagged with boolean isSaved (displayed as my cookbook).
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
    private Set<Mealplan> mealplans = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_recipe",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<Recipe> recipes;

    // CONSTRUCTORS
    public User() {
    }

    public User(Long id, String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Mealplan> mealplans, Set<Recipe> recipes) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mealplans = mealplans;
        this.recipes = recipes;
    }

    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Mealplan> mealplans, Set<Recipe> recipes) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mealplans = mealplans;
        this.recipes = recipes;
    }

    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Recipe> recipes) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.recipes = recipes;
    }

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

    public Set<Mealplan> getMealplans() {
        return mealplans;
    }

    public void setMealplans(Set<Mealplan> mealplans) {
        this.mealplans = mealplans;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    // METHODS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mealplans=" + mealplans +
                ", recipes=" + recipes +
                '}';
    }

    // HELPER METHODS FOR RELATIONSHIPS
    public void addRecipe(Recipe recipe){
        this.recipes.add(recipe);
        recipe.getUsers().add(this);
    }

    public void removeRecipe(Recipe recipe){
        this.recipes.remove(recipe);
        recipe.getUsers().remove(this);
    }

    public void addMealplan(Mealplan mealplan){
        this.mealplans.add(mealplan);
        mealplan.setUser(this);
    }

    public void removeMealplan(Mealplan mealplan){
        this.mealplans.remove(mealplan);
        mealplan.setUser(null);
    }

    // ENCRYPT USER PASSWORD
    public void encryptPassword() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }

    // CHECK USER PASSWORD
    public boolean isMatched(String password) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        return b.matches(password, this.password);
    }
}
