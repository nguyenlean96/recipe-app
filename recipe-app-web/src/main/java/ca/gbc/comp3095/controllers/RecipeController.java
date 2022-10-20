package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/", "/list"})
    public ModelAndView list() {

        Recipe r1 = new Recipe(
                "Fluffy Cake Doughnuts",
                "This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes.This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes.This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes.This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes.This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes.",
                20, 10, 12,
                "2 cups all-purpose flour\n" +
                        "\n" +
                        "¾ cup white sugar\n" +
                        "\n" +
                        "2 teaspoons baking powder\n" +
                        "\n" +
                        "¼ teaspoon ground nutmeg\n" +
                        "\n" +
                        "¼ teaspoon ground cinnamon\n" +
                        "\n" +
                        "1 teaspoon salt\n" +
                        "\n" +
                        "¾ cup milk\n" +
                        "\n" +
                        "2 eggs, beaten\n" +
                        "\n" +
                        "1 teaspoon vanilla extract\n" +
                        "\n" +
                        "1 tablespoon shortening\n" +
                        "\n" +
                        "1 cup confectioners' sugar\n" +
                        "\n" +
                        "2 tablespoons hot water\n" +
                        "\n" +
                        "½ teaspoon almond extract",
                " Preheat oven to 325 degrees F (165 degrees C). Lightly grease a doughnut pan.\n" +
                        "\n" +
                        "Mix flour, sugar, baking powder, nutmeg, cinnamon and salt. Stir in milk, eggs, vanilla and shortening. Beat together until well blended.\n" +
                        "\n" +
                        "Fill each doughnut cup approximately 3/4 full. Bake 8 to 10 minutes in the preheated oven, until doughnuts spring back when touched. Allow to cool slightly before removing from pan.\n" +
                        "\n" +
                        "To make glaze, blend confectioners' sugar, hot water and almond extract in a small bowl. Dip doughnuts in the glaze when serving.",
                "EASY"
        );
        Recipe r2 = new Recipe(
                "Baked Apple Cider Donuts",
                "All the world's great donuts are fried, except there are a few rare examples of when they're not—and this incredibly delicious and easy-to-make apple cider donut is one notable exception.",
                15, 20, 12,
                "2 cups all-purpose flour\n" +
                        "\n" +
                        "¾ teaspoon baking powder\n" +
                        "\n" +
                        "¾ teaspoon baking soda\n" +
                        "\n" +
                        "¼ teaspoon fine salt\n" +
                        "\n" +
                        "1 teaspoon ground cinnamon\n" +
                        "\n" +
                        "1 pinch ground cardamom\n" +
                        "\n" +
                        "1 pinch freshly grated nutmeg\n" +
                        "\n" +
                        "½ cup white sugar\n" +
                        "\n" +
                        "½ cup packed brown sugar\n" +
                        "\n" +
                        "½ cup warm milk\n" +
                        "\n" +
                        "6 tablespoons unsalted butter, melted, divided\n" +
                        "\n" +
                        "¾ teaspoon vanilla extract\n" +
                        "\n" +
                        "1 large egg",
                " Preheat the oven to 375 degrees F (190 degrees C). Butter two 6-cup donut pans.\n" +
                        "\n" +
                        "Pour apple cider into a saucepan and place over medium heat. Bring to a simmer and let it cook, watching carefully, until the cider is reduced to 1/2 cup. If it reduces too much, add enough water to make 1/2 cup. Set aside until needed.\n" +
                        "\n" +
                        "Add flour, baking powder, baking soda, salt, 1 teaspoon cinnamon, cardamom, and nutmeg to a large bowl. Mix with a whisk until combined and set aside until needed.\n" +
                        "\n" +

                        "If desired, while still slightly warm, brush the donuts lightly with remaining melted butter. Mix 1 cup white sugar and 1 tablespoon cinnamon together for topping in a shallow dish; toss in donuts to coat. Let cool completely before serving. ",
                "MODERATE"
        );
         recipeService.save(r1);
         recipeService.save(r2);
        ModelAndView mv = new ModelAndView();

        mv.addObject("recipes", (List<Recipe>) recipeService.findAll());
        mv.setViewName("/recipes/recipe-list");
        return mv;
    }

    @RequestMapping("/details")
    public ModelAndView details(@RequestParam String id) {
        ModelAndView mv = new ModelAndView();
        String view = "recipes/recipe-view";
        try {
            if (id == null) {
                return new ModelAndView("redirect:/").addObject("message", "Please select a recipe");
            }

            mv.addObject("recipe", recipeService.findById(Long.parseLong(id)));
            mv.setViewName("recipes/recipe-view");
        } catch (Exception e) {
            System.out.println(e);
        }
        return mv;
    }

}
