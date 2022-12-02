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
import java.util.List;

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
// *********************************************************************************/

    private static boolean testMode = false;
    private DbContext context;

    public RecipeController(DbContext context) {
        this.context = context;
    }

    @GetMapping({"", "/"})
    public ModelAndView list(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("RECIPE_USER");
        User curr = (User) context.users.findByUsername(username);
        List<Recipe> saved_recipes = (List<Recipe>) List.copyOf(curr.getCookbook_recipes());
        ModelAndView mv = new ModelAndView();

        mv.addObject("recipes", saved_recipes);
        mv.setViewName("/recipes/index");
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
            if (recipe.getImageUrl().isEmpty()) {
                recipe.setImageUrl("/images/mDishes/food-placeholder.png");
            }
            recipe.setCreator(curr);
            recipe.addUser(curr);
            context.recipes.save(recipe);
            curr.addRecipeToCreatedRecipes(recipe);
            curr.addRecipeToCookbook(recipe);
            System.out.println(recipe);
            User saved_user = this.context.users.save(curr);
            System.out.println(saved_user);

        } catch (Exception e) {
            e.getMessage();
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

            mv.addObject("recipe", req_recipe);
//            mv.addObject("reformat_ingredients", (List<String>) Arrays.asList(req_recipe.getIngredients().split("\\r?\\n")));
            mv.addObject("reformat_directions", (List<String>) Arrays.asList(req_recipe.getDirections().split("\\r?\\n")));
            mv.setViewName("recipes/view");
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Is logged in? " + String.valueOf(isLoggedIn(req)));
        System.out.println("Logged in as: " + String.valueOf(req.getSession().getAttribute("RECIPE_USER")));
        return isLoggedIn(req) ? mv.addObject("isLoggedIn", isLoggedIn(req)).addObject("username", "Hi " + getUser(req).getFirstName() + "!") : mv;
    }
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        User cur = getUser(req);
        Recipe recipe = (Recipe) context.recipes.findById(id);
        cur.removeRecipeFromCookbook(recipe);
        cur.removeRecipeFromCreatedRecipes(recipe);
        context.users.save(cur);
        context.recipes.deleteById(id);
        mv.setViewName("redirect:/api/v1/recipes");
        return autoDirect(req, mv);
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