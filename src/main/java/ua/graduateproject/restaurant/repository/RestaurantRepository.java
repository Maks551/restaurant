package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant get(int restaurantId, int userId);

    Restaurant getByAddress(String address, int userId);

    Restaurant save(Restaurant restaurant, int userId);

    boolean delete(int restaurantId, int userId);

    List<Restaurant> getAll(int userId);

    Restaurant getWithMenu(int restaurantId, int userId);

    Restaurant getWithUser(int restaurant, int userId);
}