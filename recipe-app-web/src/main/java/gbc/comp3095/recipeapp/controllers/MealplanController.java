package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.Mealplan;
import gbc.comp3095.models.Recipe;
import gbc.comp3095.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import gbc.comp3095.services.MealplanService;
import gbc.comp3095.services.RecipeService;
import gbc.comp3095.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mealplans")
public class MealplanController {
    //*********************************************************************************
    //* Project: Your Recipe App
    //* Assignment: Assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588  - 101292266    - 101303158            - 101311327
    //* Date: 2022-10-23
    //* Description: Mealplanner controller to handle requests for mealplanner operations and return the appropriate view to the user based on the request
    // *********************************************************************************/
    private UserService userService;
    private RecipeService recipeService;
    private MealplanService mealplanService;

    @Autowired
    public MealplanController(UserService userService, RecipeService recipeService, MealplanService mealplanService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.mealplanService = mealplanService;
    }

    @GetMapping({"", "/"})
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        if (!isLoggedIn(req)) {
            return new ModelAndView("redirect:/");
        }
        User curr_user = (User) userService.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER")));
        List<Mealplan> saved_plans = (List<Mealplan>) new ArrayList<Mealplan>(curr_user.getMealplans());

        mv.addObject("plans", saved_plans == null ? new ArrayList<Mealplan>() : saved_plans);
        System.out.println(saved_plans);

        mv.setViewName("meals/index");
        return autoDirect(req, mv);
    }

    @GetMapping("/add")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        Mealplan new_plan = new Mealplan();
        User curr = getUser(req);
        List<Recipe> saved_recipes = (List<Recipe>) recipeService.findAll();
        mv.addObject("req_recipes", saved_recipes);
        if (saved_recipes.isEmpty()) {
            // mv.addObject("req_recipes", null);
            mv.addObject("message_title", "Recipes Not Found");
            mv.addObject("message", "There is no recipes saved.\nPlease add a recipe first");
            mv.addObject("recipe_add_link", "/api/v1/recipes/add");
        }

        mv.addObject("req_plan", new_plan);

        mv.addObject("action", "/api/v1/mealplans/save");
        mv.addObject("btn_label", "Add new plan");
        mv.setViewName("meals/edit");
        return autoDirect(req, mv);
    }

    @PostMapping("/save")
    public ModelAndView save(@RequestParam("recipe_id") Long rid, @RequestParam("date") String date , HttpServletRequest req, Mealplan plan) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/api/v1/mealplans");
        plan.setDate(date);
        Recipe r = (Recipe) recipeService.findById(rid);
        User u = (User) userService.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER")));
        plan.setUser(u);
        plan.setRecipe(r);
        u.addMealplan(plan);

        try {
            mealplanService.save(plan);
            userService.save(u);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            mv.setViewName("redirect:/api/v1/mealplans/add");
            mv.addObject("recipe_id", plan.getRecipe().getId());
            mv.addObject("req_plan", plan);
        }
        return autoDirect(req, mv);
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        Mealplan req_plan = mealplanService.findById(id);
        mv.addObject("req_plan", req_plan);

        List<Recipe> saved_recipes = (List<Recipe>) recipeService.findAll();

        mv.addObject("req_recipes", saved_recipes);
        mv.addObject("chosen_recipe", req_plan.getRecipe());
        if (saved_recipes.isEmpty()) {
            // mv.addObject("req_recipes", null);
            mv.addObject("message_title", "Recipes Not Found");
            mv.addObject("message", "There is no recipes saved.\nPlease add a recipe first");
            mv.addObject("recipe_add_link", "/api/v1/recipes/add");
        }

        mv.addObject("action", "/api/v1/mealplans/save");
        mv.addObject("btn_label", "Save changes");
        mv.setViewName("meals/edit");
        return autoDirect(req, mv);
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        String view = "recipes/view";
        try {
            if (id == null) {
                return new ModelAndView("redirect:/").addObject("message", "Please select a meal plan");
            }

            mv.addObject("recipe", mealplanService.findById(id));
            mv.setViewName("meals/view");
        } catch (Exception e) {
            System.out.println(e);
        }
        return autoDirect(req, mv);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        User cur = getUser(req);
        Mealplan plan = mealplanService.findById(id);
        cur.removeMealplan(plan);
        mealplanService.delete(plan);

        mv.setViewName("redirect:/api/v1/mealplans");
        return autoDirect(req, mv);
    }

    public boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        System.out.println("Current session check: " + username);
        return (!(username == null));
    }

    public ModelAndView autoDirect(HttpServletRequest req, ModelAndView mv) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        if (username == null)
            return new ModelAndView("redirect:/");

         mv.addObject("isLoggedIn", isLoggedIn(req)).addObject("username", "Hi " + userService.findByUsername(username).getFirstName() + "!");
        return mv;
    }
    public User getUser(HttpServletRequest req){
        return isLoggedIn(req) ? (User) userService.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER"))) : null;
    }
}