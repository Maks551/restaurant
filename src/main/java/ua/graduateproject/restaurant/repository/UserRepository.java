package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.User;

import java.util.List;

public interface UserRepository {

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    User save(User user);

    boolean delete(int id);

    User getWithRestaurant(int id);
}
