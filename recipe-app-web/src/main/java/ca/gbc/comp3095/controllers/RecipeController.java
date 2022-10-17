package ca.gbc.comp3095.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(path="/recipe")
public class RecipeController {

    @RequestMapping({"", "/", "/list"})
    public String list(Model model) {
        model.addAttribute("breadcrumb", Arrays.asList(
                "/",
                "recipe",
                "list"
        ));
        model.addAttribute("recipes", Arrays.asList(
            "recipe 01",
            "recipe 02",
            "recipe 03",
            "recipe 04"
        ));

        return "recipes/recipe-list";
    }

    @RequestMapping("/details")
    public ModelAndView details(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("recipes/recipe-view");

        return mv;
    }

}
