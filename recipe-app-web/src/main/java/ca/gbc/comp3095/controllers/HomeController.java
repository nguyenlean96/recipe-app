package ca.gbc.comp3095.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping({"","/","/home","/index"})
    public ModelAndView home(Model model, HttpSession session) {

        System.out.println("Home view access detected");

        ModelAndView mv = new ModelAndView();
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

        mv.setViewName("index");

        return mv;
    }

    @GetMapping("about")
    public String about(Model model) {
        model.addAttribute("viewName", "about");

        return "about";
    }

    @GetMapping
    public boolean isLoggedIn(HttpSession session) {
        List<String> username = (List<String>) session.getAttribute("RECIPE_USER");
        return (!(username == null));
    }
}
