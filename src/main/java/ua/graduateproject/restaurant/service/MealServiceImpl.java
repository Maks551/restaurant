package ua.graduateproject.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.repository.MealRepository;

import java.util.List;

import static ua.graduateproject.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service("mealService")
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Override
    public Meal getWithRestaurant(int id, int restaurantId) {
        return checkNotFoundWithId(repository.getWithRestaurant(id, restaurantId), id);
    }

    @Override
    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public void update(Meal meal, int restaurantId) {
        checkNotFoundWithId(repository.save(meal, restaurantId), meal.getId());
    }

    @Override
    public Meal create(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, restaurantId);
    }
}
