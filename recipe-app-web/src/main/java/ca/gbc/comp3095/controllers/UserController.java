package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.User;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/api/v1/users"})
public class UserController {
    @RequestMapping({"", "/", "/view"})
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","<User's username>");

        mv.setViewName("/users/profile");
        return mv;
    }
    @PostMapping("/login")
    public ModelAndView login() {
        String view_name = "users/uac/login";
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title", "Lout Out");


        mv.setViewName("redirect:/");
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

    @PostMapping("/reg")
    public ModelAndView reg_process(User user) {
        String view = "redirect:/api/v1/users/profile";
        ModelAndView mv = new ModelAndView();

        return mv;
    }
    @GetMapping("/new-user")
    public ModelAndView reg_display() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("action", "/api/v1/users/reg");

        mv.setViewName("users/user-edit");
        return mv;
    }

    @GetMapping("/account/edit")
    public ModelAndView edit(@RequestParam() String id) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/users/user-edit");
        return mv;
    }

}
