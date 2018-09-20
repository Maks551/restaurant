package ua.graduateproject.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.repository.RestaurantRepository;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

import static ua.graduateproject.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant create(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant, userId);
    }

    @Override
    public void update(Restaurant restaurant, int userId) {
        checkNotFoundWithId(repository.save(restaurant, userId), restaurant.getId());
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Restaurant getByAddress(String address) {
        Assert.notNull(address, "address must not be null");
        return repository.getByAddress(address);
    }

    @Override
    public Restaurant getWithMenu(int id) {
        return checkNotFoundWithId(repository.getWithMenu(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
