package ua.graduateproject.restaurant.service;

import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal get(int id, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    List<Meal> getAll(int restaurantId);

    void update(Meal meal, int restaurantId) throws NotFoundException;

    Meal create(Meal meal, int restaurantId);
}
