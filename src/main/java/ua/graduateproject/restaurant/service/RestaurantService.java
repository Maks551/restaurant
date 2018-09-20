package ua.graduateproject.restaurant.service;

import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant, int userId);

    void update(Restaurant restaurant, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    Restaurant getByUser(int id, int userId) throws NotFoundException;

    Restaurant getByAddress(String address) throws NotFoundException;

    Restaurant getWithMenu(int id);

    Restaurant getWithUser(int id);

    List<Restaurant> getAll();
}
