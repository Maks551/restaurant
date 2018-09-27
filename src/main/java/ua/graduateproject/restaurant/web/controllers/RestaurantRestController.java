package ua.graduateproject.restaurant.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.service.RestaurantService;
import ua.graduateproject.restaurant.web.SecurityUtil;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ua.graduateproject.restaurant.util.ValidationUtil.assureIdConsistent;
import static ua.graduateproject.restaurant.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    static final String REST_URL = "/rest/profile/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RestaurantService service;

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return service.getWithMenu(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {} for user {}", id, userId);
        service.delete(id, 100004);
    }

    @GetMapping("all")
    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return service.getAll();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        log.info("update restaurant {}", id);
        int userId = SecurityUtil.authUserId();
        service.update(restaurant, userId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        int userId = SecurityUtil.authUserId();
        log.info("create restaurant {} for user {}", restaurant, userId);
        Restaurant created = service.create(restaurant, userId);

        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{userId}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResponse).body(created);
    }
}
