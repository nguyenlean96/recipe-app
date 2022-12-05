package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.Recipe;
import gbc.comp3095.models.User;
import gbc.comp3095.recipeapp.config.DbContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes/favourite")
public class FavouriteController {
    //*********************************************************************************
    //*                                *** NEW ***
    //* Project: Your Recipe App
    //* Assignment: Assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588  - 101292266    - 101303158            - 101311327
    //* Date: 2022-10-23
    //* Description: This controller works as an extension from the RecipeController to handle
    //* the favourite recipes only
    // *********************************************************************************/
    // This static attribute is used to avoid redundantly repeated call to this uri
    private static String furl = "/api/v1/recipes/favourite";
    private DbContext context;
    public FavouriteController(DbContext context) {
        this.context = context;
    }

    @GetMapping({"", "/"})
    public ModelAndView index(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("favourite/index");
        try {
            User curr = getUser(req);
            List<Recipe> saved_recipes = (List<Recipe>) List.copyOf(curr.getFavourite_recipes());
            mv.addObject("recipes", saved_recipes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return autoDirect(req, mv);
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + furl);
        try {
            User curr = getUser(req);
            Recipe req_recipe = this.context.recipes.findById(id);
            curr.addRecipeToFavourite(req_recipe);
            this.context.users.save(curr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return mv;
    }

    @GetMapping("/remove")
    public ModelAndView remove(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + furl);
        try {
            User curr = getUser(req);
            System.out.println(curr);
            Recipe req_recipe = this.context.recipes.findById(id);
            System.out.println(req_recipe);
            curr.removeRecipeFromFavorite(req_recipe);
            this.context.users.save(curr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return mv;
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
