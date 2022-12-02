package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.Ingredient;
import gbc.comp3095.models.Recipe;
import gbc.comp3095.models.User;
import gbc.comp3095.recipeapp.config.DbContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/shopping-list")
public class ShoppingListController {
    private DbContext context;
    public ShoppingListController(DbContext context) {
        this.context = context;
    }
    @GetMapping({"", "/"})
    public ModelAndView view(HttpServletRequest req, HttpServletResponse res) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("shoppinglist/index");
        User curr = getUser(req);
        List<Ingredient> saved_recipe = (List<Ingredient>) List.copyOf(curr.getShoppingList());
        mv.addObject("list", saved_recipe);
        try {

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return autoDirect(req, mv);
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/api/v1/shopping-list");
        try {
            User curr = getUser(req);
            Ingredient req_ingredient = this.context.ingredients.findById(id);
            if (req_ingredient != null) {
                curr.addToShoppingList(req_ingredient);

            }
            this.context.users.save(curr);
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
        if (username == null)
            return new ModelAndView("redirect:/");

        mv.addObject("isLoggedIn", isLoggedIn(req)).addObject("username", "Hi " + this.context.users.findByUsername(username).getFirstName() + "!");
        return mv;
    }
    public User getUser(HttpServletRequest req){
        return isLoggedIn(req) ? (User) this.context.users.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER"))) : null;
    }
}
