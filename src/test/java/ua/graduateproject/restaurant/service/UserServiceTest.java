package ua.graduateproject.restaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ua.graduateproject.restaurant.RestaurantTestData;
import ua.graduateproject.restaurant.model.Role;
import ua.graduateproject.restaurant.model.User;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT_1;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT_2;
import static ua.graduateproject.restaurant.UserTestData.*;

class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    void create() {
        User user = getCreatedUser();
        User created = service.create(user);
        user.setId(created.getId());
        assertMatch(service.getAll(), ADMIN_1, ADMIN_2, user, USER_1, USER_2, USER_3, USER_4);
    }

    @Test
    void duplicateEmailCreate() {
        User user = getCreatedDuplicateEmail();
        assertThrows(DataAccessException.class, () -> service.create(user));
    }

    @Test
    void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN_1, ADMIN_2, USER_2, USER_3, USER_4);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    void get() {
        assertMatch(service.get(USER_ID), USER_1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    void getByEmail() {
        User actual = service.getByEmail(USER_EMAIL);
        assertMatch(actual, USER_1);
    }

    @Test
    void update() {
        User user = new User(USER_1);
        user.setName("New user");
        user.setEmail("mew@gmail.com");
        user.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(new User(user));
        assertMatch(service.get(user.getId()), user);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), USERS);
    }

    @Test
    void enable() {
        service.enable(USER_ID, false);
        assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        assertTrue(service.get(USER_ID).isEnabled());
    }

    @Test
    void getWithRestaurant() {
        User actual = service.getWithRestaurant(ADMIN_ID);
        assertMatch(actual, ADMIN_1);
        RestaurantTestData.assertMatch(actual.getRestaurants(), RESTAURANT_1, RESTAURANT_2);
    }

    @Test
    void getWithRestaurantNotFound() {
        assertThrows(NotFoundException.class, () -> service.getWithRestaurant(1));
    }

    @Test
    void testValidation() {
        validateRootCause(() -> service.create(new User(null, "  ", "invalid@yandex.ru", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password",  Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "invalid@yandex.ru", "", Role.ROLE_USER)), ConstraintViolationException.class);
    }
}