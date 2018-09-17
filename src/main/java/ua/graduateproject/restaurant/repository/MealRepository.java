package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal get(int id);

    Meal save(Meal meal, int userId);

    boolean delete(int id);

    List<Meal> getAll();
}
