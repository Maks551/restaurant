package ua.graduateproject.restaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.graduateproject.restaurant.MealTestData;
import ua.graduateproject.restaurant.UserTestData;
import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ua.graduateproject.restaurant.RestaurantTestData.*;
import static ua.graduateproject.restaurant.UserTestData.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    void create() {
        Restaurant created = getCreated();
        service.create(created, ADMIN_ID);
        assertMatch(service.getAll(), created, RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void createNotFound() {
        Restaurant created = getCreated();
        assertThrows(NotFoundException.class, () -> service.create(created, 1));
    }

    @Test
    void createNotFoundNotAdmin() {
        Restaurant created = getCreated();
        assertThrows(NotFoundException.class, () -> service.create(created, USER_ID));
    }

    @Test
    void update() {
        Restaurant updated = getUpdatedByAdmin_1();
        service.update(updated, ADMIN_ID);
    }

    @Test
    void updateNotFound() {
        Restaurant updated = getUpdatedByAdmin_1();
        assertThrows(NotFoundException.class, () -> service.update(updated, 1));
    }

    @Test
    void updateNotFoundNotAdmin() {
        Restaurant updated = getUpdatedByAdmin_1();
        assertThrows(NotFoundException.class, () -> service.update(updated, ADMIN2_ID));
    }

    @Test
    void delete() {
        service.delete(RESTAURANT1_ID, ADMIN_ID);
        assertMatch(service.getAll(), RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(RESTAURANT1_ID, ADMIN2_ID));
    }

    @Test
    void get() {
        assertMatch(service.get(RESTAURANT1_ID), RESTAURANT_1);
    }

    @Test
    void getByUser() {
        Restaurant actual = service.getByUser(RESTAURANT1_ID, ADMIN_ID);
        assertMatch(actual, RESTAURANT_1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    void getByAddress() {
        Restaurant actual = service.getByAddress(ADDRESS_R1);
        assertMatch(actual, RESTAURANT_1);
    }

    @Test
    void getWithMenu() {
        Restaurant actual = service.getWithMenu(RESTAURANT1_ID);
        assertMatch(actual, RESTAURANT_1);
        MealTestData.assertMatch(actual.getMenu(), MealTestData.RESTAURANT1_MEALS);
    }

    @Test
    void getWithMenuNotFound() {
        assertThrows(NotFoundException.class, () -> service.getWithMenu(USER_ID));
    }

    @Test
    void getWithUser() {
        Restaurant actual = service.getWithUser(RESTAURANT1_ID);
        assertMatch(actual, RESTAURANT_1);
        UserTestData.assertMatch(actual.getUser(), ADMIN_1);
    }

    @Test
    void getWithUserNotFound() {
        assertThrows(NotFoundException.class, () -> service.getWithUser(USER_ID));
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), RESTAURANT_LIST);
    }
}