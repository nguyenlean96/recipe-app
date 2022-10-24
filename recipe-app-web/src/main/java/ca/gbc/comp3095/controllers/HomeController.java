package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.models.User;
import ca.gbc.comp3095.services.RecipeService;
import ca.gbc.comp3095.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class HomeController {
//*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588  - 101292266    - 101303158            - 101311327
//* Date: 2022-10-23
//* Description: Home controller to handle requests for home operations and return the appropriate view to the user based on the request - Landing page operations
// *********************************************************************************//
    private static boolean testMode = false;
    private RecipeService recipeService;
    private UserService userService;
    @Autowired
    public HomeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping({"","/","home","index"})
    public ModelAndView home(HttpServletRequest req) {
        System.out.println("Home view access detected");
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());
        List<Recipe> saved_recipes = (List<Recipe>) recipeService.findAll();

        mv.addObject("recipes", saved_recipes);
        mv.addObject("loggedin", isLoggedIn(req));
        mv.setViewName("index");
        return isLoggedIn(req) ? mv.addObject("username", "Hi " + userService.findByUsername((String) req.getSession().getAttribute("RECIPE_USER")).getFirstName() + "!") : mv;
    }

    @GetMapping("/about")
    public ModelAndView about(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        // mv.addObject("loggedin", isLoggedIn(req));
        mv.addObject("viewName", "about");

        mv.setViewName("about");
        return isLoggedIn(req) ? mv.addObject("loggedin", isLoggedIn(req)).addObject("username", req.getSession().getAttribute("RECIPE_USER")) : mv;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("recipe_name") String name, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("search");
        System.out.println(name);
        mv.addObject("recipes", null);
        mv.addObject("recipe_name", name);
        try {
            List<Recipe> saved_recipes = (List<Recipe>) recipeService.findAll();
            System.out.println(saved_recipes);
            List<Recipe> res_recipes = new ArrayList<>();
            for (Recipe r : saved_recipes) {
                if (r.getName().toLowerCase().contains(name.toLowerCase())) {
                    res_recipes.add(r);
                }
            }
            mv.addObject("recipes", res_recipes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            mv.addObject("message", e.getMessage());
        }

        return isLoggedIn(req) ? mv.addObject("loggedin", isLoggedIn(req)).addObject("username", req.getSession().getAttribute("RECIPE_USER")) : mv;
    }
    @GetMapping("error")
    public ModelAndView error(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        // mv.addObject("loggedin", isLoggedIn(req));

        mv.setViewName("redirect:/");
        return autoDirect(req, mv);
    }

    public boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        System.out.println("Is logged in? " + String.valueOf(!(username == null)));
        return (!(username == null));
    }

    public ModelAndView autoDirect(HttpServletRequest req, ModelAndView mv) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        return (username == null) ? new ModelAndView("redirect:/") : mv.addObject("loggedin", isLoggedIn(req)).addObject("username", "Hi " + userService.findByUsername(username).getFirstName() + "!");
    }
}