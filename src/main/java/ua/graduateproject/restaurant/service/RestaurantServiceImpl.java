package ua.graduateproject.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.repository.RestaurantRepository;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public Restaurant getByAddress(String address) {
        return null;
    }

    @Override
    public Restaurant getWithMenu(int id) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
