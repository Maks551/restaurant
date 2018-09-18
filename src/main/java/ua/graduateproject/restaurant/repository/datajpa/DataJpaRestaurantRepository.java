package ua.graduateproject.restaurant.repository.datajpa;

import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.repository.RestaurantRepository;

import java.util.List;

public class DataJpaRestaurantRepository implements RestaurantRepository {

    @Override
    public Restaurant get(int restaurantId) {
        return null;
    }

    @Override
    public Restaurant getByUser(int restaurantId, int userId) {
        return null;
    }

    @Override
    public Restaurant getByAddress(String address) {
        return null;
    }

    @Override
    public Restaurant getByAddress(String address, int userId) {
        return null;
    }

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        return null;
    }

    @Override
    public boolean delete(int restaurantId, int userId) {
        return false;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }

    @Override
    public List<Restaurant> getAllByUser(int userId) {
        return null;
    }

    @Override
    public Restaurant getWithMenu(int restaurantId, int userId) {
        return null;
    }

    @Override
    public Restaurant getWithUser(int restaurant, int userId) {
        return null;
    }
}
