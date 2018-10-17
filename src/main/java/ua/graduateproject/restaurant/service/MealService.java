package ua.graduateproject.restaurant.service;

import ua.graduateproject.restaurant.model.Meal;

import java.util.List;

public interface MealService {

    Meal get(int id, int restaurantId);

    void delete(int id, int restaurantId);

    List<Meal> getAll(int restaurantId);

    void update(Meal meal, int restaurantId);

    Meal create(Meal meal, int restaurantId);

    Meal getWithRestaurant(int id, int restaurantId);
}
