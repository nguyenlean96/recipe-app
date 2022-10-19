package ca.gbc.comp3095.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/v1/mealplans")
public class MealPlanController {

    @GetMapping({"/", "/details"})
    public ModelAndView details() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("templates/meals/meal-view");
        return mv;
    }

}
