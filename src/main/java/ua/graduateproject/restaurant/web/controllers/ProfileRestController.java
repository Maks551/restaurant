package ua.graduateproject.restaurant.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.graduateproject.restaurant.model.User;
import ua.graduateproject.restaurant.service.UserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ua.graduateproject.restaurant.util.ValidationUtil.assureIdConsistent;
import static ua.graduateproject.restaurant.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class ProfileRestController {
    static final String REST_URL = "/rest/profile";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService service;

    @Autowired
    public ProfileRestController(UserService service) {
        this.service = service;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public User get() {
        int id = authUserId();
        log.info("get {}", id);
        return service.get(id);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        int id = authUserId();
        log.info("delete user {}", id);
        service.delete(id);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        int id = authUserId();
        assureIdConsistent(user, id);
        log.info("update {] with id={}", user, id);
        service.update(user);
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Український текст";
    }
}
