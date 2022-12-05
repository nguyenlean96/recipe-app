package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.Ingredient;
import gbc.comp3095.models.Recipe;
import gbc.comp3095.models.User;
import gbc.comp3095.recipeapp.config.DbContext;
import gbc.comp3095.recipeapp.config.ShoppingListExport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shopping-list")
public class ShoppingListController {
    //*********************************************************************************
    //* Project: Your Recipe App
    //* Assignment: Assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588  - 101292266    - 101303158            - 101311327
    //* Date: 2022-10-23
    //* Description: ShoppingList controller is to handle requests for Shoppinglist CRUD operations
    //* and return the appropriate view based on the request
    // *********************************************************************************/
    private DbContext context;
    public ShoppingListController(DbContext context) {
        this.context = context;
    }
    @GetMapping({"", "/"})
    public ModelAndView view(HttpServletRequest req, HttpServletResponse res) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("shoppinglist/index");

        List<Recipe> retrieved_recipes = new ArrayList<>();

        try {
            User curr = getUser(req);
            List<Ingredient> user_shoppinglist = (List<Ingredient>) List.copyOf(curr.getShoppingList());
            mv.addObject("list", user_shoppinglist);
            if (user_shoppinglist.size() > 0) {
                for (Ingredient i : user_shoppinglist) {
                    if (retrieved_recipes.size() > 0) {
                        if (!retrieved_recipes.contains(i.getRecipe())) {
                            retrieved_recipes.add(i.getRecipe());
                        }
                    } else {
                        retrieved_recipes.add(i.getRecipe());
                    }
                }
            }
            mv.addObject("corr_recipes", retrieved_recipes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return autoDirect(req, mv);
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();

        try {
            User curr = getUser(req);
            Ingredient req_ingredient = this.context.ingredients.findById(id);
            if (req_ingredient != null) {
                curr.addToShoppingList(req_ingredient);
                mv.setViewName("redirect:/api/v1/recipes/details?id=" + req_ingredient.getRecipe().getId());
                this.context.users.save(curr);
            } else {
                throw new Exception("Failed to add ingredient to shopping list. Ingredient not found.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            mv.setViewName("redirect:/");
        }
        return mv;
    }

    @GetMapping("delete")
    public ModelAndView delete(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/api/v1/shopping-list");
        try {
            User curr = getUser(req);
            Ingredient req_ingredient = this.context.ingredients.findById(id);
            if (req_ingredient != null) {
                curr.removeIngredientFromShoppingList(req_ingredient);
            }
            this.context.users.save(curr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mv;
    }

    @GetMapping("/download")
    public void download(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=shoppinglist_" + currentDateTime + ".xlsx";

        res.setHeader(headerKey, headerValue);
        User curr = getUser(req);
        List<Ingredient> shoppinglist = (List<Ingredient>) List.copyOf(curr.getShoppingList());
        ShoppingListExport exporter = new ShoppingListExport(shoppinglist);
        exporter.export(res);
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
