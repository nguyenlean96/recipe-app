package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.models.*;
import gbc.comp3095.recipeapp.config.DbContext;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/eventplans")
public class EventPlanController {
    private DbContext context;

    public EventPlanController(DbContext context) {
        this.context = context;
    }

    @GetMapping({"", "/"})
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        if (!isLoggedIn(req)) {
            return new ModelAndView("redirect:/");
        }
        User curr_user = (User) this.context.users.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER")));
        List<EventPlan> saved_plans = (List<EventPlan>) this.context.events.findAll();

        mv.addObject("event_plans", saved_plans);
        System.out.println(saved_plans);

        mv.setViewName("eventplans/index");
        return autoDirect(req, mv);
    }

    @GetMapping("/add")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        User curr = getUser(req);
        EventPlan new_plan = new EventPlan();
        mv.addObject("req_event", new_plan);
        List<Recipe> saved_recipes = (List<Recipe>) this.context.recipes.findAll();
        mv.addObject("req_recipes", saved_recipes);
        if (saved_recipes.isEmpty()) {
            // mv.addObject("req_recipes", null);
            mv.addObject("message_title", "Recipes Not Found");
            mv.addObject("message", "There is no recipes saved.\nPlease add a recipe first");
            mv.addObject("recipe_add_link", "/api/v1/recipes/add");
        }



        mv.addObject("action", "/api/v1/eventplans/save");
        mv.addObject("btn_label", "Add new plan");
        mv.setViewName("eventplans/edit");
        return autoDirect(req, mv);
    }

    @PostMapping("/save")
    public ModelAndView save(@RequestParam("recipe_item") String chosen_ones, HttpServletRequest req, EventPlan plan) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/api/v1/eventplans");

        User u = (User) this.context.users.findByUsername(String.valueOf(req.getSession().getAttribute("RECIPE_USER")));

        Set<Recipe> chosen_recipes = new HashSet<>();

        try {
            if (chosen_ones != null) {
                if (!chosen_ones.equals("") && !chosen_ones.equals("-99")) {
                    String[] chosens = chosen_ones.split(",");

                    if (chosens.length > 0) {
                        for (String r : chosens) {
                            Recipe found_one = this.context.recipes.findById(Long.parseLong(r));
                            if (found_one != null) {
                                chosen_recipes.add(found_one);
                            }
                            System.out.println(found_one);
                        }
                    }
                }
            }
            plan.setEventRecipes(chosen_recipes);
            plan.setEventUser(u);
            EventPlan saved_plan = this.context.events.save(plan);
            System.out.println(saved_plan);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            mv.setViewName("redirect:/api/v1/eventplans/add");
            mv.addObject("req_plan", plan);
        }

        return mv;
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        EventPlan req_plan = this.context.events.findById(id);
        mv.addObject("req_event", req_plan);

        List<Recipe> saved_recipes = (List<Recipe>) this.context.recipes.findAll();

        mv.addObject("req_recipes", saved_recipes);
        mv.addObject("chosen_recipe", req_plan.getEventRecipes());
        if (saved_recipes.isEmpty()) {
            // mv.addObject("req_recipes", null);
            mv.addObject("message_title", "Recipes Not Found");
            mv.addObject("message", "There is no recipes saved.\nPlease add a recipe first");
            mv.addObject("recipe_add_link", "/api/v1/recipes/add");
        }

        mv.addObject("action", "/api/v1/eventplans/save");
        mv.addObject("btn_label", "Save changes");
        mv.setViewName("eventplans/edit");
        return autoDirect(req, mv);
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        String view = "recipes/view";
        try {
            if (id == null) {
                return new ModelAndView("redirect:/").addObject("message", "Please select a meal plan");
            }

            mv.addObject("recipe", this.context.mealplans.findById(id));
            mv.setViewName("eventplans/view");
        } catch (Exception e) {
            System.out.println(e);
        }
        return autoDirect(req, mv);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        User cur = getUser(req);
        EventPlan plan = this.context.events.findById(id);
        cur.removeEventPlan(plan);
        this.context.events.delete(plan);
        this.context.users.save(cur);

        mv.setViewName("redirect:/api/v1/eventplans");
        return autoDirect(req, mv);
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
