package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal get(int id, int restaurantId);

    Meal save(Meal meal, int restaurantId);

    boolean delete(int id, int restaurantId);

    List<Meal> getAll(int restaurantId);

    Meal getWithRestaurant(int id, int restaurantId);
}
