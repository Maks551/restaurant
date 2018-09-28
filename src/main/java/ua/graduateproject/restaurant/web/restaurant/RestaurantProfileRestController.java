package ua.graduateproject.restaurant.web.restaurant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.graduateproject.restaurant.model.Restaurant;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RestaurantProfileRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class RestaurantProfileRestController extends AbstractRestaurantRestController {
    static final String REST_URL = "/rest/profile/restaurants";

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping("all")
    public List<Restaurant> getAll() {
        return super.getAll();
    }
}
