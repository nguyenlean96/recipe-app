package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.Mealplan;
import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.services.MealplanService;
import ca.gbc.comp3095.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mealplans")
public class MealplanController {


    private final MealplanService mealplanService;

    @Autowired
    public MealplanController(MealplanService mealplanService) {
        this.mealplanService = mealplanService;
    }

    @GetMapping({"", "/"})
    public ModelAndView list() {
        List<Mealplan> saved_plans = (List<Mealplan>) mealplanService.findAll();

        ModelAndView mv = new ModelAndView();

        mv.addObject("recipes", saved_plans);
        mv.setViewName("/recipes/index");
        return mv;
    }
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        Mealplan new_plan = new Mealplan();
        mv.addObject("req_plan", new_plan);
        /* SUSPENDING
        Calendar calendar = Calendar.getInstance();
        int today_inWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int today_inMonth = calendar.get(Calendar.DAY_OF_MONTH);

         */

        mv.addObject("action", "/api/v1/mealplans/save");
        mv.addObject("btn_label", "Add new plan");
        mv.setViewName("meals/edit");
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView edit(Mealplan plan) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/api/v1/mealplans");
        try {
            mealplanService.save(plan);
        } catch (Exception e) {
            System.out.println(e);
            mv.addObject("message", e.getMessage());
        }

        return mv;
    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Mealplan req_plan = mealplanService.findById(id);
        mv.addObject("req_plan", req_plan);


        mv.addObject("action", "/api/v1/mealplans/save");
        mv.addObject("btn_label", "Save changes");
        mv.setViewName("meals/edit");
        return mv;
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView();
        String view = "recipes/view";
        try {
            if (id == null) {
                return new ModelAndView("redirect:/").addObject("message", "Please select a meal plan");
            }

            mv.addObject("recipe", mealplanService.findById(id));
            mv.setViewName("meals/view");
        } catch (Exception e) {
            System.out.println(e);
        }
        return mv;
    }
    public boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        return (!(username == null));
    }
    public ModelAndView autoDirect(HttpServletRequest req, ModelAndView mv) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("RECIPE_USER");
        return (username == null) ? new ModelAndView("redirect:/") : mv.addObject("loggedin", isLoggedIn(req));
    }

}
