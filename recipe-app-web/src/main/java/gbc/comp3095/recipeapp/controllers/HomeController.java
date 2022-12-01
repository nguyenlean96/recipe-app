package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.Recipe;
import gbc.comp3095.models.User;
import gbc.comp3095.recipeapp.config.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import gbc.comp3095.services.RecipeService;
import gbc.comp3095.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
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
// *********************************************************************************/

    private static boolean testMode = false;
    private DbContext context;

    public HomeController(DbContext context) {
        this.context = context;
    }

    @GetMapping({"","/","home","index"})
    public ModelAndView home(HttpServletRequest req) {
        System.out.println("Home view access detected");
        ModelAndView mv = new ModelAndView();
        // mv.addObject("user", new User());
        List<Recipe> saved_recipes = (List<Recipe>) context.recipes.findAll();

        if (saved_recipes.size() < 5) {
            Recipe rep = new Recipe("Phá lấu", "/images/mDishes/food-placeholder.png", "description", 9, 8, 1, "direction", "impossible");
            rep.setRecipeIngredients(new HashSet<>());
            context.recipes.save(rep);
            try {
                if (isLoggedIn(req)) {
                    User curr = getCurrUser(req);
                    rep.setUser(curr);
                    context.users.save(curr);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        mv.setViewName("index");
        mv.addObject("recipes", saved_recipes);
        // return isLoggedIn(req) ? mv.addObject("username", "Hi " + context.users.findByUsername((String) req.getSession().getAttribute("RECIPE_USER")).getFirstName() + "!").addObject("isLoggedIn", isLoggedIn(req)) : mv;
        return autoDirect(req, mv);
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
            List<Recipe> saved_recipes = (List<Recipe>) context.recipes.findAll();
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
    private User getCurrUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        return this.context.users.findByUsername(username);
    }
    private boolean isLoggedIn(HttpServletRequest req) {
        boolean loggedIn = this.getCurrUser(req) != null;
        return loggedIn;
    }

    private ModelAndView autoDirect(HttpServletRequest req, ModelAndView mv) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        User curr_user = this.context.users.findByUsername(username);
        if (!(curr_user == null)) {
            mv.addObject("isLoggedIn", true)
                    .addObject("username", "Hi " + curr_user.getFirstName() + "!");
        }

        return mv;
    }
}