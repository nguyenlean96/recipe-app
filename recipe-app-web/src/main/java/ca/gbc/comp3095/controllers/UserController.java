package ca.gbc.comp3095.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/users"})
public class UserController {
    @RequestMapping({"", "/", "/index", "/index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping({"/login", "/login.html"})
    public String login() {
        return "login";
    }

}