package ua.graduateproject.restaurant.web.meal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.graduateproject.restaurant.model.Meal;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = MealProfileRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class MealProfileRestController extends AbstractMealRestController{
    static final String REST_URL = "/rest/profile/meals";

    @GetMapping("/{id}/restaurant-id/{restaurant}")
    public Meal get(@PathVariable("id") int id, @PathVariable("restaurant") int restaurantId) {
        return super.get(id, restaurantId);
    }

    @GetMapping(value = "restaurant-id/{restaurant}")
    public List<Meal> getAll(@PathVariable("restaurant") int restaurantId) {
        return super.getAll(restaurantId);
    }
}
