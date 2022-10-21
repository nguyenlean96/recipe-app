package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/"})
    public ModelAndView list(HttpServletRequest req) {
        List<Recipe> saved_recipes = (List<Recipe>) recipeService.findAll();
        ModelAndView mv = new ModelAndView();

        mv.addObject("recipes", saved_recipes);
        mv.setViewName("/recipes/recipe-list");
        return autoDirect(req, mv);
    }
    @GetMapping("/add")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        Recipe new_recipe = new Recipe();
        new_recipe.setImageUrl("/images/mDishes/food-placeholder.png");
        mv.addObject("req_recipe", new_recipe);

        mv.addObject("action", "/api/v1/recipes/save");
        mv.addObject("btn_label", "Add new recipe");
        mv.setViewName("recipes/recipe-edit");
        return autoDirect(req, mv);
    }

    @PostMapping("/save")
    public ModelAndView edit(HttpServletRequest req, Recipe recipe) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/api/v1/recipes");
        try {
            recipeService.save(recipe);
        } catch (Exception e) {
            System.out.println(e);
            mv.addObject("message", e.getMessage());
        }

        return autoDirect(req, mv);
    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        Recipe req_recipe = recipeService.findById(id);
        mv.addObject("req_recipe", req_recipe);


        mv.addObject("action", "/api/v1/recipes/save");
        mv.addObject("btn_label", "Save changes");
        mv.setViewName("recipes/recipe-edit");
        return autoDirect(req, mv);
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        String view = "recipes/recipe-view";
        try {
            if (id == null) {
                return new ModelAndView("redirect:/").addObject("message", "Please select a recipe");
            }

            mv.addObject("recipe", recipeService.findById(id));
            mv.setViewName("recipes/recipe-view");
        } catch (Exception e) {
            System.out.println(e);
        }
        return autoDirect(req, mv);
    }

    public boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession();
        List<String> username = (List<String>) session.getAttribute("RECIPE_USER");
        return (!(username == null));
    }
    public ModelAndView autoDirect(HttpServletRequest req, ModelAndView mv) {
        HttpSession session = req.getSession();
        List<String> username = (List<String>) session.getAttribute("RECIPE_USER");
        return (!(username == null)) ? new ModelAndView("redirect:/") : mv;
    }
}
