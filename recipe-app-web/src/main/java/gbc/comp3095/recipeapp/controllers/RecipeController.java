package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.Ingredient;
import gbc.comp3095.models.Recipe;
import gbc.comp3095.models.UnitOfMeasurement;
import gbc.comp3095.models.User;
import gbc.comp3095.recipeapp.config.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import gbc.comp3095.services.RecipeService;
import gbc.comp3095.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {
//*********************************************************************************
//* Project: Your Recipe App
//* Assignment: Assignment 2
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588  - 101292266    - 101303158            - 101311327
//* Date: 2022-10-23
//* Description: Recipe controller to handle requests for recipe operations and return the appropriate view to the user based on the request
// *********************************************************************************/

    private static boolean testMode = false;
    private DbContext context;

    public RecipeController(DbContext context) {
        this.context = context;
    }

    @GetMapping({"", "/"})
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        String username = (String) req.getSession().getAttribute("RECIPE_USER");
        mv.setViewName("/recipes/index");
        try {
            User curr = (User) context.users.findByUsername(username);
            List<Recipe> saved_recipes = (List<Recipe>) List.copyOf(curr.getCreated_recipes());

            mv.addObject("recipes", saved_recipes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return autoDirect(req, mv);
    }

    @GetMapping("/add")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        Recipe new_recipe = this.context.recipes.save(new Recipe());
        mv.addObject("req_recipe", new_recipe);
        mv.addObject("new_ingredient", new Ingredient("", 0, UnitOfMeasurement.UNIT, new_recipe));

        mv.addObject("action", "/api/v1/recipes/save");
        mv.addObject("btn_label", "Add new recipe");
        mv.setViewName("recipes/edit");
        return testMode ? mv : autoDirect(req, mv);
    }

    @PostMapping("/save")
    public ModelAndView save(HttpServletRequest req, Recipe recipe) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/api/v1/recipes");

        User curr = (User) context.users.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER")));

        try {
            if (recipe.getName().equals("")) {
                this.context.recipes.delete(this.context.recipes.findById(recipe.getId()));
                throw new Exception("Name cannot be blank");
            }
            if (recipe.getDescription().equals("")) {
                this.context.recipes.delete(this.context.recipes.findById(recipe.getId()));
                throw new Exception("Description cannot be blank");
            }
            if (recipe.getDirections().equals("")) {
                this.context.recipes.delete(this.context.recipes.findById(recipe.getId()));
                throw new Exception("Direction cannot be blank");
            }
            // Set default image since this feature is still under developing
            if (recipe.getImageUrl().isEmpty()) {
                recipe.setImageUrl("/images/mDishes/food-placeholder.png");
            }
            if (recipe.getCreator() == null) {
                recipe.setCreator(curr);
                Recipe saved_one = this.context.recipes.save(recipe);
                curr.addRecipeToCreatedRecipes(saved_one);

            } else if (!recipe.getCreator().getUsername().equals(curr.getUsername())) {
                System.out.println("Hijacking recipe #" + recipe.getId() + " - " + recipe.getName() + " from " + recipe.getCreator() + "...");
                System.out.println("Saving a copy version of " + recipe.getName() + " to user: " + curr.getUsername());

                Recipe new_recipe = new Recipe();
                new_recipe.setName(recipe.getName());
                new_recipe.setImageUrl(recipe.getImageUrl());
                new_recipe.setDescription(recipe.getDescription());
                new_recipe.setPrepTime(recipe.getPrepTime());
                new_recipe.setCookTime(recipe.getCookTime());
                new_recipe.setServings(recipe.getServings());
                new_recipe.setDirections(recipe.getDirections());
                new_recipe.setDifficulty(recipe.getDifficulty());

                new_recipe.setCreator(curr);
                Recipe saved_one = this.context.recipes.save(new_recipe);
                List<Ingredient> old_ones = (List<Ingredient>) List.copyOf(recipe.getRecipeIngredients());
                for (Ingredient i : old_ones) {
                    Ingredient new_one = new Ingredient(i.getDescription(), i.getQuantity(), i.getUnitOfMeasurement(), saved_one);
                    saved_one.addIngredient(new_one);
                }
                curr.addRecipeToCreatedRecipes(saved_one);
                System.out.println(saved_one);
            } else {
                Recipe saved_one = this.context.recipes.save(recipe);
                curr.addRecipeToCreatedRecipes(saved_one);
            }
            this.context.users.save(curr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return testMode ? mv : autoDirect(req, mv);
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        try {
            Recipe req_recipe = context.recipes.findById(id);
            mv.addObject("req_recipe", req_recipe);
            mv.addObject("new_ingredient", new Ingredient("", 0, UnitOfMeasurement.UNIT, req_recipe));

            mv.addObject("action", "/api/v1/recipes/save");
            mv.addObject("btn_label", "Save changes");
            mv.setViewName("recipes/edit");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return testMode ? mv : autoDirect(req, mv);
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        String view = "recipes/view";
        try {
            if (id == null) {
                return new ModelAndView("redirect:/").addObject("message", "Please select a recipe");
            }
            Recipe req_recipe = context.recipes.findById(id);
            List<Ingredient> original_ones = List.copyOf(req_recipe.getRecipeIngredients());
            req_recipe.setRecipeIngredients(new HashSet<>());
            for (Ingredient i : original_ones) {
                if (!req_recipe.getRecipeIngredients().contains(i)) {
                    req_recipe.addIngredient(i);
                } else {
                    this.context.ingredients.delete(i);
                }
            }

            mv.addObject("recipe", req_recipe);

            // mv.addObject("reformat_ingredients", (List<String>) Arrays.asList(req_recipe.getIngredients().split("\\r?\\n"))); >>> DEPRECATED >>>

            mv.addObject("reformat_directions", (List<String>) Arrays.asList(req_recipe.getDirections().split("\\r?\\n")));
            mv.setViewName("recipes/view");
        } catch (Exception e) {
            System.out.println(e);
        }

        return isLoggedIn(req) ? mv.addObject("isLoggedIn", isLoggedIn(req)).addObject("username", "Hi " + getUser(req).getFirstName() + "!") : mv;
    }
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/api/v1/recipes");
        User curr = getUser(req);
        List<Recipe> saved_recipes = (List<Recipe>) this.context.recipes.findAll();
        try {
            Recipe recipe = context.recipes.findById(id);
            // System.out.println(recipe);
            curr.removeRecipeFromCreatedRecipes(recipe);
            if (curr.getFavourite_recipes().contains(recipe)){
                curr.removeRecipeFromFavorite(recipe);
            }
            this.context.recipes.delete(recipe);
            this.context.users.save(curr);
            if (saved_recipes.contains(recipe)) throw new Exception("Deleting " + recipe.getName() + " failed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mv;
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("name") String name, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();

        return isLoggedIn(req) ? mv.addObject("loggedin", isLoggedIn(req)).addObject("username", req.getSession().getAttribute("RECIPE_USER")) : mv;
    }

    public boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        return (!(username == null));
    }

    public ModelAndView autoDirect(HttpServletRequest req, ModelAndView mv) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        if (username == null)
            return new ModelAndView("redirect:/");

        mv.addObject("isLoggedIn", isLoggedIn(req)).addObject("username", "Hi " + context.users.findByUsername(username).getFirstName() + "!");
        return mv;
    }
    public User getUser(HttpServletRequest req){
        return isLoggedIn(req) ? (User) context.users.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER"))) : null;
    }
}