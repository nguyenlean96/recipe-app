package ca.gbc.comp3095.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/account", "account"})
public class UserAccessController {

    @RequestMapping("/login")
    public ModelAndView LoginAccess(Model model) {
        model.addAttribute("page_title", "User Login");

        ModelAndView mv = new ModelAndView();
        System.out.println("Loging in access...");

        mv.setViewName("/users/uac/login");
        return mv;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(Model model) {
        ModelAndView mv = new ModelAndView();


        mv.setViewName("/users/uac/logout");
        return mv;
    }

    @RequestMapping("/account/profile")
    public ModelAndView view(Model model) {
        ModelAndView mv = new ModelAndView();


        mv.setViewName("/users/profile");
        return mv;
    }

    @RequestMapping("/account/edit")
    public ModelAndView edit(Model model) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/users/user-edit");
        return mv;
    }
}
