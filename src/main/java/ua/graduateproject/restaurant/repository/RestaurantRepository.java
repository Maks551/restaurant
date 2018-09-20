package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantRepository {

    Restaurant get(int id);

    Restaurant getByUser(int id, int userId);

    Restaurant getByAddress(String address);

    Restaurant save(Restaurant restaurant, int userId);

    boolean delete(int id, int userId);

    List<Restaurant> getAll();

    Restaurant getWithMenu(int id);

    Restaurant getWithUser(int id);
}
