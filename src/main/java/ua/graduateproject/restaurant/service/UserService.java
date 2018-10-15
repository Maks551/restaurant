package ua.graduateproject.restaurant.service;

import ua.graduateproject.restaurant.model.User;
import ua.graduateproject.restaurant.to.UserTo;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    void update(User user);

    void update(UserTo userTo);

    List<User> getAll();

    void enable(int id, boolean enable);

    User getWithRestaurant(int id);
}