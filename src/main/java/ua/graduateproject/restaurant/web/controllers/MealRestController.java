package ua.graduateproject.restaurant.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.service.MealService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ua.graduateproject.restaurant.util.ValidationUtil.assureIdConsistent;
import static ua.graduateproject.restaurant.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class MealRestController {
    static final String REST_URL = "/rest/profile/meals";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    @GetMapping("/{id}/restaurant-id/{restaurant}")
    public Meal get(@PathVariable("restaurant") int restaurantId, @PathVariable("id") int id) {
        log.info("get meal {} for restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    @DeleteMapping("/{id}/restaurant-id/{restaurant}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restaurant") int restaurantId, @PathVariable("id") int id) {
        log.info("delete meal {} for restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    @GetMapping(value = "restaurant-id/{restaurant}")
    public List<Meal> getAll(@PathVariable("restaurant") int restaurantId) {
        log.info("getAll meals for restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    @PutMapping(value = "/{id}/restaurant-id/{restaurant}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal,
                       @PathVariable("restaurant") int restaurantId, @PathVariable("id") int id) {
        assureIdConsistent(meal, id);
        log.info("update {} for restaurant {}", meal, restaurantId);
        service.update(meal, restaurantId);
    }

    @PostMapping(value = "/restaurant-id/{restaurant}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @PathVariable("restaurant") int restaurantId) {
        checkNew(meal);
        log.info("create meal {} for restaurant {}", meal, restaurantId);
        Meal created = service.create(meal, restaurantId);

        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResponse).body(created);
    }
}
