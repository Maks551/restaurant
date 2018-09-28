package ua.graduateproject.restaurant.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.service.MealService;

import java.util.List;

import static ua.graduateproject.restaurant.util.ValidationUtil.assureIdConsistent;
import static ua.graduateproject.restaurant.util.ValidationUtil.checkNew;

public abstract class AbstractMealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MealService service;

    public Meal get(int id, int restaurantId) {
        log.info("get meal {} for restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete meal {} for restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    public List<Meal> getAll(int restaurantId) {
        log.info("getAll meals for restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    public void update(Meal meal, int id, int restaurantId) {
        assureIdConsistent(meal, id);
        log.info("update {} for restaurant {}", meal, restaurantId);
        service.update(meal, restaurantId);
    }

    public Meal create(Meal meal, int restaurantId) {
        checkNew(meal);
        log.info("create meal {} for restaurant {}", meal, restaurantId);
        return service.create(meal, restaurantId);
    }
}
