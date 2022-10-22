package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.User;
import ca.gbc.comp3095.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {
    private static boolean testMode = true;
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/", "/view"})
    public ModelAndView view(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_title","<User's username>");

        mv.setViewName("/users/view");
        return autoDirect(req, mv);
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
    @PostMapping("/save")
    public ModelAndView save(HttpServletRequest req, User u) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/api/v1/users/login");
        try {
            if (userService.findByUsername(u.getUsername()) != null) {
                return new ModelAndView("users/edit").addObject("user", u).addObject("message", "Username " + u.getUsername() + " exists!");
            }
            userService.save(u);
        } catch (Exception e) {
            System.out.println(e);
            mv.addObject("message", e.getMessage());
        }

        return mv;
    }
    @GetMapping("/logout")
    public ModelAndView logout(WebRequest req, SessionStatus status) {
        ModelAndView mv = new ModelAndView();
        status.setComplete();
        req.removeAttribute("RECIPE_USER", WebRequest.SCOPE_SESSION);
        // store.cleanupAttribute(req, "RECIPE_USER");

        mv.setViewName("redirect:/");
        return mv;
    }
    @GetMapping("/new-user")
    public ModelAndView reg() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("action", "/api/v1/users/save");
        mv.addObject("user", new User());
        mv.addObject("is_edit", false);

        mv.setViewName("users/edit");
        return mv;
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("is_edit", true);
        User req_user = userService.findById(id);
        mv.addObject("user", req_user);

        mv.addObject("action", "/api/v1/users/save");
        mv.setViewName("/users/edit");
        return testMode ? mv : autoDirect(req, mv);
    }

    @PostMapping("/validator")
    public ModelAndView validator(HttpServletRequest req, HttpSession session, User user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        try {
            User exists = (User) userService.findByUsername(user.getUsername());
            if (exists == null) {
                return new ModelAndView("users/uac/login");
            }
            System.out.println(exists);
            if (!(user.getPassword().equals(exists.getPassword()))) {
                return new ModelAndView("users/uac/login");
            }
            session.setAttribute("RECIPE_USER", user.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return mv;
    }

    public boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        System.out.println("Current session check: " + username);
        return (!(username == null));
    }
    public ModelAndView autoDirect(HttpServletRequest req, ModelAndView mv) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        return (username == null) ? new ModelAndView("redirect:/") : mv.addObject("loggedin", isLoggedIn(req));
    }
}
