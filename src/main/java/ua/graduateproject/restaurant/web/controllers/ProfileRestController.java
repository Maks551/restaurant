package ua.graduateproject.restaurant.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.graduateproject.restaurant.model.User;
import ua.graduateproject.restaurant.to.UserTo;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ua.graduateproject.restaurant.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class ProfileRestController extends AbstractUserRestController{
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public User get() {
        int id = authUserId();
        return super.get(id);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        int id = authUserId();
        super.delete(id);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserTo userTo) {
        int id = authUserId();
        super.update(userTo, id);
    }
}
