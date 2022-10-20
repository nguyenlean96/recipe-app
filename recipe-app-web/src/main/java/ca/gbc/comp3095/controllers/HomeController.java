package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
public class HomeController {

    private RecipeService recipeService;
    @Autowired
    public HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"","/","home","index"})
    public ModelAndView home(Model model) {
        String name = "An";
        System.out.println("Home view access detected");
        model.addAttribute("name", name);
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);
        List<Recipe> saved_recipes = (List<Recipe>) recipeService.findAll();
        if (saved_recipes.isEmpty()) {
            mv.addObject("fake_recipes", Arrays.asList(
                    "Baked Apple Cider Donuts",
                    "Apple Cider Stew",
                    "Glazed Apple Cider Cake",
                    "Apple Cider Pancakes",
                    "Apple Cider Sauce and Pork Loin Chops",
                    "Spiked Caramel Apple Cider",
                    "Slow Cooker Apple Cider Braised Pork",
                    "Ashley's Apple Cider Doughnuts",
                    "Butternut Squash and Apple Cider Soup",
                    "Apple Cider Pulled Pork with Caramelized Onion and Apples"

            ));
        } else {
            mv.addObject("recipes", saved_recipes);
        }

        System.out.println("User: " + name);
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/about")
    public ModelAndView about(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("viewName", "about");

        mv.setViewName("about");
        return mv;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("redirect:/");
        return mv;
    }

    public boolean isLoggedIn(HttpSession session) {
        List<String> username = (List<String>) session.getAttribute("RECIPE_USER");
        return (!(username == null));
    }
}
