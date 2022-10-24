package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.models.User;
import ca.gbc.comp3095.services.RecipeService;
import ca.gbc.comp3095.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {
//*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588  - 101292266    - 101303158            - 101311327
//* Date: 2022-10-23
//* Description: Recipe controller to handle requests for recipe operations and return the appropriate view to the user based on the request
// *********************************************************************************//
    private static boolean testMode = false;
    private RecipeService recipeService;
    private UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public ModelAndView list(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("RECIPE_USER");
        User curr = (User) userService.findByUsername(username);
        List<Recipe> saved_recipes = (List<Recipe>) recipeService.findAll();
        ModelAndView mv = new ModelAndView();

        mv.addObject("recipes", saved_recipes);
        mv.setViewName("/recipes/index");
        return testMode ? mv : autoDirect(req, mv);
    }

    @GetMapping("/add")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        Recipe new_recipe = new Recipe();
        new_recipe.setImageUrl("/images/mDishes/food-placeholder.png");
        mv.addObject("req_recipe", new_recipe);

        mv.addObject("action", "/api/v1/recipes/save");
        mv.addObject("btn_label", "Add new recipe");
        mv.setViewName("recipes/edit");
        return testMode ? mv : autoDirect(req, mv);
    }

    @PostMapping("/save")
    public ModelAndView save(HttpServletRequest req, Recipe recipe) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/api/v1/recipes");

        User curr = (User) userService.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER")));
        curr.addRecipe(recipe);
        try {

        } catch (Exception e) {
            e.getMessage();
        }
        try {
            recipeService.save(recipe);
            userService.save(curr);
        } catch (Exception e) {
            System.out.println(e);
            mv.addObject("message", e.getMessage());
        }

        return testMode ? mv : autoDirect(req, mv);
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        Recipe req_recipe = recipeService.findById(id);
        mv.addObject("req_recipe", req_recipe);


        mv.addObject("action", "/api/v1/recipes/save");
        mv.addObject("btn_label", "Save changes");
        mv.setViewName("recipes/edit");

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
            Recipe req_recipe = recipeService.findById(id);

            mv.addObject("recipe", req_recipe);
            mv.addObject("reformat_ingredients", (List<String>) Arrays.asList(req_recipe.getIngredients().split("\\r?\\n")));
            mv.addObject("reformat_directions", (List<String>) Arrays.asList(req_recipe.getDirections().split("\\r?\\n")));
            mv.setViewName("recipes/view");
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Is logged in? " + String.valueOf(isLoggedIn(req)));
        System.out.println("Logged in as: " + String.valueOf(req.getSession().getAttribute("RECIPE_USER")));
        return isLoggedIn(req) ? mv.addObject("loggedin", isLoggedIn(req)).addObject("username", req.getSession().getAttribute("RECIPE_USER")) : mv;
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
        return (username == null) ? new ModelAndView("redirect:/") : mv.addObject("loggedin", isLoggedIn(req)).addObject("username", "Hi " + userService.findByUsername(username).getFirstName() + "!");
    }
}
