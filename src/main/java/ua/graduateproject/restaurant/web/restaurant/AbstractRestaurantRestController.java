package ua.graduateproject.restaurant.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.service.RestaurantService;
import ua.graduateproject.restaurant.web.SecurityUtil;

import java.util.List;

import static ua.graduateproject.restaurant.util.ValidationUtil.assureIdConsistent;
import static ua.graduateproject.restaurant.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RestaurantService service;

    public Restaurant get(int id) {
        log.info("get {}", id);
        return service.getWithMenu(id);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return service.getAll();
    }

    public void update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        log.info("update restaurant {}", id);
        int userId = SecurityUtil.authUserId();
        service.update(restaurant, userId);
    }

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        int userId = SecurityUtil.authUserId();
        log.info("create restaurant {} for user {}", restaurant, userId);
        return service.create(restaurant, userId);
    }
}
