package ca.gbc.comp3095.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping({"/users"})
public class UserController {
    @RequestMapping({"", "/", "/view"})
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView();


        mv.setViewName("/users/profile");
        return mv;
    }

    @RequestMapping({"/login"})
    public String login() {

        return "login";
    }

    @RequestMapping("/logout")
    public ModelAndView logout(Model model) {
        ModelAndView mv = new ModelAndView();


        mv.setViewName("/users/uac/logout");
        return mv;
    }

    @RequestMapping("/account/edit")
    public ModelAndView edit(Model model) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/users/user-edit");
        return mv;
    }

}
