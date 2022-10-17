package ca.gbc.comp3095.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping({"","/","home","index"})
    public ModelAndView home(Model model) {
        String name = "An";
        System.out.println("Home view access detected");
        model.addAttribute("name", name);
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);

        System.out.println("User: " + name);
        mv.setViewName("index");

        return mv;
    }

    @GetMapping("about")
    public String about(Model model) {
        model.addAttribute("viewName", "about");

        return "about";
    }
}
