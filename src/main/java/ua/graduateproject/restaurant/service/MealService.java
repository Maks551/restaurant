package ua.graduateproject.restaurant.service;

import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal get(int id, int menuId) throws NotFoundException;

    void delete(int id, int menuId) throws NotFoundException;

    List<Meal> getAll(int menuId);

    void update(Meal meal, int menuId) throws NotFoundException;

    Meal create(Meal meal, int menuId);
}
