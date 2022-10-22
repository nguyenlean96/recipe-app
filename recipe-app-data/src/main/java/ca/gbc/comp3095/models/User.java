package ca.gbc.comp3095.models;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="users")
public class User{
    // each user has the profile attribiues
    // other than that each user can CREATE many recipes (saved in the recipe table as foreign key to user)
    // each user can save many recipes to their cookbook (saved in the cookbook_recipe table)
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

    @OneToMany(mappedBy = "user")
    private Set<Cookbook> cookbooks = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipes = new HashSet<>();

    public User() {
    }

    // all attributes
    public User(Long id, String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Mealplan> mealplans, Set<Cookbook> cookbooks, Set<Recipe> recipes) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mealplans = mealplans;
        this.cookbooks = cookbooks;
        this.recipes = recipes;
    }

    // no id
    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Mealplan> mealplans, Set<Cookbook> cookbooks, Set<Recipe> recipes) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mealplans = mealplans;
        this.cookbooks = cookbooks;
        this.recipes = recipes;
    }

    // basics
    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // user with just recipes
    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Recipe> recipes) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.recipes = recipes;
    }

    // user with recipe and cookbook
    public User(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Recipe> recipes, Set<Cookbook> cookbooks) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.recipes = recipes;
        this.cookbooks = cookbooks;
    }

    // setters and getters
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

    public Set<Cookbook> getCookbooks() {
        return cookbooks;
    }

    public void setCookbooks(Set<Cookbook> cookbooks) {
        this.cookbooks = cookbooks;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(mealplans, user.mealplans) &&
                Objects.equals(cookbooks, user.cookbooks) &&
                Objects.equals(recipes, user.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, firstName, lastName, phoneNumber, mealplans, cookbooks, recipes);
    }

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
                ", cookbooks=" + cookbooks +
                ", recipes=" + recipes +
                '}';
    }

    // helper methods for adding and removing mealplans, cookbooks, and recipes
    // add mealplan
    public void addMealplan(Mealplan mealplan) {
        this.mealplans.add(mealplan);
    }

    // remove mealplan
    public void removeMealplan(Mealplan mealplan) {
        this.mealplans.remove(mealplan);
    }

    // edit mealplan
    public void editMealplan(Mealplan mealplan) {
        this.mealplans.remove(mealplan);
        this.mealplans.add(mealplan);
    }

    // add cookbook
    public void addCookbook(Cookbook cookbook) {
        this.cookbooks.add(cookbook);
    }

    // remove cookbook
    public void removeCookbook(Cookbook cookbook) {
        this.cookbooks.remove(cookbook);
    }

    // edit cookbook
    public void editCookbook(Cookbook cookbook) {
        this.cookbooks.remove(cookbook);
        this.cookbooks.add(cookbook);
    }
    ////// adding more cookbooks and removing them is an option that might be added to the application later; however, at this time there will be one cookbook
    // shown to the user as "My Cookbook" and the user will not be able to add or remove cookbooks
    // by default add a cookbook whenever a user is created
    public void addDefaultCookbook() {
        Cookbook cookbook = new Cookbook(this, "My Cookbook", "My default cookbook");
        this.cookbooks.add(cookbook);
    }

    // add recipe
    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    // remove recipe
    public void removeRecipe(Recipe recipe) {
        this.recipes.remove(recipe);
    }

    // edit recipe
    public void editRecipe(Recipe recipe) {
        this.recipes.remove(recipe);
        this.recipes.add(recipe);
    }
}
