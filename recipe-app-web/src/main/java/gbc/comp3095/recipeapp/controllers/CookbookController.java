package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.Recipe;
import gbc.comp3095.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import gbc.comp3095.services.RecipeService;
import gbc.comp3095.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cookbooks")
@ComponentScan("ca.gbc.comp3095.models")
public class CookbookController {
    // to controll user recipe table rows where IsSaved is 1 ----- if a user saves a recipe, it will be added to the user recipe table and IsSaved will be set to 1
    private UserService userService;
    private RecipeService recipeService;
    @Autowired
    public CookbookController(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/","/list"})
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/index");
        User curr = getUser(req);

        List<Recipe> saved_recipes = (List<Recipe>) List.copyOf(curr.getCookbook_recipes());

        mv.addObject("recipes", saved_recipes);

        return autoDirect(req, mv);
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam("id") Long rid, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/view");

        Recipe req_recipe = (Recipe) recipeService.findById(rid);
        User curr = getUser(req);
        curr.addRecipeToCookbook(req_recipe);
        userService.save(curr);

        mv.setViewName("redirect:/api/v1/cookbooks/");
        return autoDirect(req, mv);
    }
    @GetMapping("/edit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/edit");

        return autoDirect(req, mv);
    }

    @GetMapping("/delete")
    public ModelAndView delete(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/view");

        return autoDirect(req, mv);
    }

    @PostMapping("/save")
    public ModelAndView save(@RequestParam("id") Long id, HttpServletRequest req, Recipe recipe) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/view");
        Recipe r = (Recipe) recipeService.findById(id);
        User curr = getUser(req);
        curr.addRecipeToCookbook(recipe);
        List<Recipe> user_cookbook = (List<Recipe>) List.copyOf(curr.getCookbook_recipes());
        return autoDirect(req, mv);
    }

    public boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        System.out.println("Is logged in? " + String.valueOf(!(username == null)));
        return (!(username == null));
    }

    public ModelAndView autoDirect(HttpServletRequest req, ModelAndView mv) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        return (username == null) ? new ModelAndView("redirect:/") : mv.addObject("loggedin", isLoggedIn(req)).addObject("username", "Hi " + userService.findByUsername(username).getFirstName() + "!");
    }

    public User getUser(HttpServletRequest req){
        return isLoggedIn(req) ? (User) userService.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER"))) : null;
    }
}