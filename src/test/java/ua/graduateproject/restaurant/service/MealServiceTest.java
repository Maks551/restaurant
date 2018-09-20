package ua.graduateproject.restaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.graduateproject.restaurant.MealTestData;
import ua.graduateproject.restaurant.RestaurantTestData;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.util.exception.ErrorType;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ua.graduateproject.restaurant.MealTestData.*;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT1;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT2_ID;

class MealServiceTest extends AbstractServiceTest {

    @Autowired
    private MealService service;

    @Test
    void delete() throws Exception{
        service.delete(RESTAURANT1_MEAL_ID, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), RESTAURANT1_MEAL2, RESTAURANT1_MEAL3, RESTAURANT1_MEAL4);
    }

    @Test
    void deleteNotFound() throws Exception{
        assertThrows(NotFoundException.class, () ->
                service.delete(RESTAURANT1_MEAL_ID, 1));
    }

    @Test
    void get() throws Exception{
        Meal actual = service.get(RESTAURANT1_MEAL_ID, RESTAURANT1_ID);
        assertMatch(actual, RESTAURANT1_MEAL1);
    }

    @Test
    void getWithRestaurant() {
        Meal actual = service.getWithRestaurant(RESTAURANT1_MEAL_ID, RESTAURANT1_ID);
        assertMatch(actual, RESTAURANT1_MEAL1);
        RestaurantTestData.assertMatch(actual.getRestaurant(), RESTAURANT1);
    }

    @Test
    void getWithRestaurantNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.getWithRestaurant(RESTAURANT1_MEAL_ID, RESTAURANT2_ID));
    }

    @Test
    void getAll() throws Exception{
        assertMatch(service.getAll(100004), RESTAURANT1_MEALS);
    }

    @Test
    void update() throws Exception{
        Meal updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.get(updated.getId(), RESTAURANT1_ID), updated);
    }

    @Test
    void updatedNotFound() throws Exception{
        NotFoundException e = assertThrows(NotFoundException.class, () -> service.update(RESTAURANT1_MEAL1, RESTAURANT2_ID));
        String msg = e.getMessage();
        System.out.println(msg);
        assertTrue(msg.contains(ErrorType.DATA_NOT_FOUND.name()));
        assertTrue(msg.contains(NotFoundException.NOT_FOUND_EXCEPTION));
        assertTrue(msg.contains(String.valueOf(RESTAURANT1_MEAL_ID)));
    }

    @Test
    void create() throws Exception{
        Meal created = getCreated();
        service.create(created, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), RESTAURANT1_MEAL1, RESTAURANT1_MEAL2, RESTAURANT1_MEAL3, RESTAURANT1_MEAL4 ,created);
    }
}