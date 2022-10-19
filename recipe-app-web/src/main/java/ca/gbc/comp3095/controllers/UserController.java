package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.User;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping({"api/v1/users"})
public class UserController {
    @RequestMapping({"", "/", "/view"})
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","<User's username>");

        mv.setViewName("/users/profile");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(User login_user) {
        String view_name = "index";
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","Lout Out");



        mv.setViewName(view_name);
        return mv;
    }

    @GetMapping({"/login"})
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","Log In");
        mv.addObject("user", new User(
                "lean96",
                "abc",
                "annl96@mail.com",
                "Le An",
                "Nguyen",
                "123"
        ));


        mv.setViewName("/users/uac/login");
        return mv;
    }

    @RequestMapping(path = "/logout")
    public ModelAndView logout(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        req.getSession().invalidate();

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/account/edit")
    public ModelAndView edit(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","E");

        mv.setViewName("/users/user-edit");
        return mv;
    }

    @GetMapping
    public boolean isLoggedIn(HttpSession session) {
        List<String> username = (List<String>) session.getAttribute("RECIPE_USER");
        return (!(username == null));
    }
}
