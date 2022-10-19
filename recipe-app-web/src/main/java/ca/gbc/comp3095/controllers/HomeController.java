package ca.gbc.comp3095.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping({"","/","/home","/index"})
    public ModelAndView home(Model model, HttpSession session) {
        List<String> names = (List<String>) session.getAttribute("USER_NAME");
        if (names != null) {
            for (String name : names) {
                System.out.println(name);
            }
        }
        System.out.println("Home view access detected");
//        model.addAttribute("name", name);
        ModelAndView mv = new ModelAndView();
//        mv.addObject("name", name);

//        System.out.println("User: " + name);
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
