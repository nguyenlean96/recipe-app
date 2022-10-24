package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.models.User;
import ca.gbc.comp3095.models.UserRecipe;
import ca.gbc.comp3095.services.RecipeService;
import ca.gbc.comp3095.services.UserRecipeService;
import ca.gbc.comp3095.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/cookbooks")
public class CookbookController {
    // to controll user recipe table rows where IsSaved is 1 ----- if a user saves a recipe, it will be added to the user recipe table and IsSaved will be set to 1
    private UserRecipeService userRecipeService;
    private UserService userService;
    private RecipeService recipeService;
    @Autowired
    public CookbookController(UserRecipeService userRecipeService, UserService userService, RecipeService recipeService) {
        this.userRecipeService = userRecipeService;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/","/list"})
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/index");
        User curr = (User) userService.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER")));
        List<Recipe> req_cookbook = new ArrayList<>();
        mv.addObject("recipes", req_cookbook);
        /*

        List<UserRecipe> cookbook_recipes = (List<UserRecipe>) userService.findAllByUserIdAndIsSaved(curr.getId(), true);

        mv.addObject("cookbook_recipes", cookbook_recipes);
        */
        return autoDirect(req, mv);
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam("id") Long rid, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/view");
        UserRecipe new_req_recipe = new UserRecipe();
        Recipe req_recipe = (Recipe) recipeService.findById(rid);
        // new_req_recipe.set
        // userRecipeService.save()

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
    public ModelAndView save(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cookbooks/view");

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
}