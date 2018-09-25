package ua.graduateproject.restaurant.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.graduateproject.restaurant.model.Vote;
import ua.graduateproject.restaurant.service.VoteService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ua.graduateproject.restaurant.util.ValidationUtil.assureIdConsistent;
import static ua.graduateproject.restaurant.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/rest/profile/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final VoteService service;

    @Autowired
    public VoteRestController(VoteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable("id") int id) {
        log.info("get vote {}", id);
        return service.get(id);
    }

    @GetMapping("restaurant-id/{restaurant}")
    public List<Vote> getAllByRestaurant(@PathVariable("restaurant") int restaurantId) {
        log.info("getAll votes for restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete vote {}", id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Vote vote, @PathVariable("id") int id) {
        assureIdConsistent(vote, id);
        log.info("update vote {}", vote);
        service.update(vote);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote) {
        checkNew(vote);
        log.info("create {}", vote);
        Vote created = service.create(vote);

        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResponse).body(created);
    }
}
