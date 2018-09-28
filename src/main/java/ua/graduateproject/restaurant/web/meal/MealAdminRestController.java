package ua.graduateproject.restaurant.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.graduateproject.restaurant.model.Meal;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = MealAdminRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class MealAdminRestController extends AbstractMealRestController{
    static final String REST_URL = "/rest/admin/meals";

    @GetMapping("/{id}/restaurant-id/{restaurant}")
    public Meal get(@PathVariable("id") int id, @PathVariable("restaurant") int restaurantId) {
        return super.get(id, restaurantId);
    }

    @DeleteMapping("/{id}/restaurant-id/{restaurant}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("restaurant") int restaurantId) {
        super.delete(id, restaurantId);
    }

    @GetMapping(value = "restaurant-id/{restaurant}")
    public List<Meal> getAll(@PathVariable("restaurant") int restaurantId) {
        return super.getAll(restaurantId);
    }

    @PutMapping(value = "/{id}/restaurant-id/{restaurant}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable("id") int id, @PathVariable("restaurant") int restaurantId) {
        super.update(meal, id, restaurantId);
    }

    @PostMapping(value = "/restaurant-id/{restaurant}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @PathVariable("restaurant") int restaurantId) {
        Meal created = super.create(meal, restaurantId);

        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResponse).body(created);
    }
}
