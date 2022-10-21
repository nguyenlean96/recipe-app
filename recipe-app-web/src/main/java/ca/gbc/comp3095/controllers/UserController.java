package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.User;
import ca.gbc.comp3095.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping({"/api/v1/users"})
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/", "/view"})
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","<User's username>");

        mv.setViewName("/users/profile");
        return mv;
    }
    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest req, HttpServletResponse res) {
        String view_name = "users/uac/login";
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());

        mv.setViewName("users/uac/login");
        return mv;
    }
    /*
    @GetMapping({"/login"})
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","Log In");



        mv.setViewName("/users/uac/login");
        return mv;
    } */

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        req.getSession().invalidate();

        mv.setViewName("redirect:/");
        return mv;
    }
    @GetMapping("/new-user")
    public ModelAndView reg() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("action", "/api/v1/users/reg");
        mv.addObject("new_user", new User());

        mv.setViewName("users/user-edit");
        return mv;
    }

    @GetMapping("/account/edit")
    public ModelAndView edit(@RequestParam() String id) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/users/user-edit");
        return mv;
    }

    @PostMapping("/validator")
    public ModelAndView validator(User user) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("redirect:/");
        return mv;
    }

    public boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession();
        List<String> username = (List<String>) session.getAttribute("RECIPE_USER");
        return (!(username == null));
    }
    public ModelAndView autoDirect(HttpServletRequest req, ModelAndView mv) {
        HttpSession session = req.getSession();
        List<String> username = (List<String>) session.getAttribute("RECIPE_USER");
        return (username == null) ? new ModelAndView("redirect:/") : mv;
    }
}
