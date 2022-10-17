package ca.gbc.comp3095.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/", ""})
public class UserAccessController {

    @RequestMapping("/login")
    public ModelAndView LoginAccess(Model model) {
        model.addAttribute("page_title", "User Login");

        ModelAndView mv = new ModelAndView();
        System.out.println("Loging in access...");

        mv.setViewName("/users/uac/login");
        return mv;
    }
}
