package ua.graduateproject.restaurant.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.graduateproject.restaurant.model.Restaurant;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController extends AbstractRestaurantRestController{
    static final String REST_URL = "/rest/admin/restaurants";
    private static final String WITH_ID = "/{id}";

    @GetMapping(WITH_ID)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @DeleteMapping(WITH_ID)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @GetMapping("all")
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @PutMapping(value = WITH_ID, consumes = APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        super.update(restaurant, id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = super.create(restaurant);

        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + WITH_ID)
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResponse).body(created);
    }
}
