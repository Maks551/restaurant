package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.User;

public interface UserRepository {

    User get(int id);

    User getAll();

    User save(int id);

    User update(int id);

    boolean delete(int id);
}
