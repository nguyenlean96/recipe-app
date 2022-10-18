package ca.gbc.comp3095.controllers;

import ca.gbc.comp3095.models.Recipe;
import ca.gbc.comp3095.services.maps.RecipeMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping(path="/recipe")
public class RecipeController {

    @RequestMapping({"", "/", "/list"})
    public ModelAndView list(Model model) {
//        Recipe r1 = new Recipe(
//                "Fluffy Cake Doughnuts",
//
//                "This recipe is very quick and easy to make. I have a donut pan, which is like a muffin pan, but has donut shaped holes rather than muffin shaped holes. These donuts are baked not fried, so they don't have so many calories. I mix up either a powdered sugar glaze or a chocolate icing to top these with. They can also be rolled in cinnamon and white sugar, but it doesn't stick very well. My husband likes them just plain. For a chocolate glaze, simply stir 1/2 cup melted chocolate chips into the glaze mixture.",
//                20, 10, 12,
//                "2 cups all-purpose flour\n" +
//                        "\n" +
//                        "¾ cup white sugar\n" +
//                        "\n" +
//                        "2 teaspoons baking powder\n" +
//                        "\n" +
//                        "¼ teaspoon ground nutmeg\n" +
//                        "\n" +
//                        "¼ teaspoon ground cinnamon\n" +
//                        "\n" +
//                        "1 teaspoon salt\n" +
//                        "\n" +
//                        "¾ cup milk\n" +
//                        "\n" +
//                        "2 eggs, beaten\n" +
//                        "\n" +
//                        "1 teaspoon vanilla extract\n" +
//                        "\n" +
//                        "1 tablespoon shortening\n" +
//                        "\n" +
//                        "1 cup confectioners' sugar\n" +
//                        "\n" +
//                        "2 tablespoons hot water\n" +
//                        "\n" +
//                        "½ teaspoon almond extract",
//                " Preheat oven to 325 degrees F (165 degrees C). Lightly grease a doughnut pan.\n" +
//                        "\n" +
//                        "In a large bowl, mix flour, sugar, baking powder, nutmeg, cinnamon and salt. Stir in milk, eggs, vanilla and shortening. Beat together until well blended.\n" +
//                        "\n" +
//                        "Fill each doughnut cup approximately 3/4 full. Bake 8 to 10 minutes in the preheated oven, until doughnuts spring back when touched. Allow to cool slightly before removing from pan.\n" +
//                        "\n" +
//                        "To make glaze, blend confectioners' sugar, hot water and almond extract in a small bowl. Dip doughnuts in the glaze when serving.",
//                "EASY"
//        );
//        Recipe r2 = new Recipe(
//                "Baked Apple Cider Donuts",
//                "All the world's great donuts are fried, except there are a few rare examples of when they're not—and this incredibly delicious and easy-to-make apple cider donut is one notable exception. Since we're not going to fry these, not only are they easier, but they're way less messy. Less time cleaning up means more time eating donuts.",
//                15, 20, 12,
//                "2 cups all-purpose flour\n" +
//                        "\n" +
//                        "¾ teaspoon baking powder\n" +
//                        "\n" +
//                        "¾ teaspoon baking soda\n" +
//                        "\n" +
//                        "¼ teaspoon fine salt\n" +
//                        "\n" +
//                        "1 teaspoon ground cinnamon\n" +
//                        "\n" +
//                        "1 pinch ground cardamom\n" +
//                        "\n" +
//                        "1 pinch freshly grated nutmeg\n" +
//                        "\n" +
//                        "½ cup white sugar\n" +
//                        "\n" +
//                        "½ cup packed brown sugar\n" +
//                        "\n" +
//                        "½ cup warm milk\n" +
//                        "\n" +
//                        "6 tablespoons unsalted butter, melted, divided\n" +
//                        "\n" +
//                        "¾ teaspoon vanilla extract\n" +
//                        "\n" +
//                        "1 large egg",
//                " Preheat the oven to 375 degrees F (190 degrees C). Butter two 6-cup donut pans.\n" +
//                        "\n" +
//                        "Pour apple cider into a saucepan and place over medium heat. Bring to a simmer and let it cook, watching carefully, until the cider is reduced to 1/2 cup. If it reduces too much, add enough water to make 1/2 cup. Set aside until needed.\n" +
//                        "\n" +
//                        "Add flour, baking powder, baking soda, salt, 1 teaspoon cinnamon, cardamom, and nutmeg to a large bowl. Mix with a whisk until combined and set aside until needed.\n" +
//                        "\n" +
//                        "Whisk 1/2 cup white sugar, brown sugar, milk, 2 tablespoons melted butter, vanilla extract, and egg together in another bowl until combined. Add the apple cider reduction and the dry ingredients. Whisk together to form a slightly thick batter; do not over mix.\n" +
//                        "\n" +
//                        "Spoon or pipe the batter into the prepared donut pans, filling them about 3/4 of the way up.\n" +
//                        "\n" +
//                        "Bake in the center of the preheated oven until the tops are lightly browned, and the donuts spring back slightly to the touch, 10 to 12 minutes. Let cool for 10 minutes in the pans before removing to a sheet pan lined with a silicone baking mat. Cut out any donut holes as necessary.\n" +
//                        "\n" +
//                        "If desired, while still slightly warm, brush the donuts lightly with remaining melted butter. Mix 1 cup white sugar and 1 tablespoon cinnamon together for topping in a shallow dish; toss in donuts to coat. Let cool completely before serving. ",
//                "MODERATE"
//        );
        // recipeMapService.save(r1);
        // recipeMapService.save(r2);
        ModelAndView mv = new ModelAndView();

//        model.addAttribute("recipes", List.of(
//                r1,
//                r2
//        ));
        mv.setViewName("/recipes/recipe-list");
        return mv;
    }

    @RequestMapping("/details")
    public ModelAndView details(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("recipes/recipe-view");

        return mv;
    }

}
