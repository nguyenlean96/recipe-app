package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.Ingredient;
import gbc.comp3095.models.Recipe;
import gbc.comp3095.recipeapp.config.DbContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/shoppinglist")
public class ShoppingListController {
    private DbContext context;

    public ShoppingListController(DbContext context) {
        this.context = context;
    }
    @GetMapping({"", "/"})
    public ModelAndView view(HttpServletRequest req, HttpServletResponse res) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("shoppinglist/index");

        List<Recipe> saved_recipe = (List<Recipe>) this.context.recipes.findAll();

        mv.addObject("list", saved_recipe);
        try {

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return mv;
    }
}
