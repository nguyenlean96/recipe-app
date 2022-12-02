package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.Recipe;
import gbc.comp3095.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import gbc.comp3095.services.RecipeService;
import gbc.comp3095.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cookbooks")
@ComponentScan("ca.gbc.comp3095.models")
public class CookbookController {
//*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588  - 101292266    - 101303158            - 101311327
//* Date: 2022-10-23
//* Description: Mealplanner controller to handle requests for mealplanner operations and return the appropriate view to the user based on the request
// *********************************************************************************/
    // to controll user recipe table rows where IsSaved is 1
    // -----
    // if a user saves a recipe, it will be added to the user recipe table and IsSaved will be set to 1
    private UserService userService;
    private RecipeService recipeService;
    @Autowired
    public CookbookController(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/","/list"})
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/index");
        User curr = getUser(req);
        List<Recipe> saved_recipes = (List<Recipe>) List.copyOf(curr.getFavourite_recipes());

        mv.addObject("recipes", saved_recipes);
         mv = autoDirect(req, mv);
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam("id") Long rid, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/view");

        try {
            Recipe req_recipe = (Recipe) recipeService.findById(rid);
            System.out.println(req_recipe);
            User curr = getUser(req);
            curr.addRecipeToFavourite(req_recipe);
            userService.save(curr);

            mv.setViewName("redirect:/api/v1/cookbooks/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            mv.setViewName("redirect:/");
        }
        mv = autoDirect(req, mv);
        return mv;
    }
    @GetMapping("/edit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/edit");

        return autoDirect(req, mv);
    }

    @GetMapping("/delete")
    public ModelAndView delete(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/view");

        return autoDirect(req, mv);
    }

    @PostMapping("/save")
    public ModelAndView save(@RequestParam("id") Long id, HttpServletRequest req, Recipe recipe) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/view");
        Recipe r = (Recipe) recipeService.findById(id);
        User curr = getUser(req);
        curr.addRecipeToFavourite(recipe);
        List<Recipe> user_cookbook = (List<Recipe>) List.copyOf(curr.getFavourite_recipes());
        return autoDirect(req, mv);
    }

    public boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        System.out.println("Is logged in? " + String.valueOf(!(username == null)));
        return (!(username == null));
    }

    private ModelAndView autoDirect(HttpServletRequest req, ModelAndView mv) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        if (username == null)
            return new ModelAndView("redirect:/");

         mv.addObject("isLoggedIn", isLoggedIn(req)).addObject("username", "Hi " + userService.findByUsername(username).getFirstName() + "!");
        return mv;
    }

    private User getUser(HttpServletRequest req){
        return isLoggedIn(req) ? (User) userService.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER"))) : null;
    }
}