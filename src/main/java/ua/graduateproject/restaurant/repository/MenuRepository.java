package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.Menu;

import java.util.List;

public interface MenuRepository {

    Menu get(int restaurantId, int userId);

    Menu save(Menu menu, int restaurantId, int userId);

    boolean delete(int id, int userId);

    List<Menu> getAllByUser(int userId);
}
