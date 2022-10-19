package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.Recipe;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping(path="api/v1/recipes")
public class RecipeController {
    // private final RecipeMapService recipeMapService;
    /*
    public RecipeController(RecipeMapService recipeMapService) {
        this.recipeMapService = recipeMapService;
    } */

    @RequestMapping({"", "/", "/list"})
    public ModelAndView list(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","All recipes");

        mv.setViewName("/recipes/recipe-list");
        return mv;
        // return recipeMapService.findAll();
    }

    @PostMapping(path = "/add")
    public ModelAndView add(Recipe recipe) {
        ModelAndView mv = new ModelAndView();


        mv.setViewName("/recipes/recipe-edit");
        return mv;
    }

    @GetMapping({"/add"})
    public ModelAndView add(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","Edit <Recipe name>");
        mv.addObject("action", "/api/v1/recipes/add");

        mv.setViewName("/recipes/recipe-edit");
        return mv;
    }
    @PostMapping("/edit")
    public ModelAndView edit(Recipe recipe) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("recipes/recipe-list");
        return mv;
    }

    @GetMapping({"/edit"})
    public ModelAndView edit(@RequestParam("id") Long recipeId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","Add new recipe");
        mv.addObject("action", "/api/v1/recipes/edit");
        mv.addObject("recipe", new Recipe());

        mv.setViewName("recipes/recipe-edit");
        return mv;
    }

    @RequestMapping("/details")
    public ModelAndView details(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","<Recipe Name>");

        mv.setViewName("recipes/recipe-view");
        return mv;
    }

}
