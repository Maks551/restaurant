package ua.graduateproject.restaurant.service;

import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    Restaurant getByAddress(String address);

    Restaurant getWithMenu(int id);

    List<Restaurant> getAll();
}
