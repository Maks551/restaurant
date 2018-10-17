package ua.graduateproject.restaurant.service;

import ua.graduateproject.restaurant.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant, int userId);

    void update(Restaurant restaurant, int userId);

    void delete(int id, int userId);

    Restaurant get(int id);

    Restaurant getByUser(int id, int userId);

    Restaurant getByAddress(String address);

    Restaurant getWithMenu(int id);

    Restaurant getWithUser(int id);

    List<Restaurant> getAll();
}
