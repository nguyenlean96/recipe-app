package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.Ingredient;
import gbc.comp3095.models.Recipe;
import gbc.comp3095.recipeapp.config.DbContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1/ingredients")
public class IngredientController {
    private DbContext context;
    public IngredientController(DbContext context) {
        this.context = context;
    }
    @PostMapping("/add")
    private ModelAndView add(HttpServletRequest req, Ingredient ing) {
        ModelAndView mv = new ModelAndView();
        Recipe saved_recipe = this.context.recipes.save(ing.getRecipe());
        saved_recipe.addIngredient(ing);
        Recipe resaved_recipe = context.recipes.save(saved_recipe);
        System.out.println(resaved_recipe);

        mv.setViewName("redirect:/api/v1/recipes/edit?id=" + ing.getRecipe().getId());
        return mv;
    }

    @GetMapping("/delete")
    private ModelAndView delete(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        try {
            Ingredient targeted_ingredient = this.context.ingredients.findById(id);
            Recipe curr_recipe = targeted_ingredient.getRecipe();
            this.context.ingredients.delete(targeted_ingredient);
            mv.setViewName("redirect:/api/v1/recipes/edit?id=" + curr_recipe.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ModelAndView("redirect:/");
        }

        return mv;
    }

}
