package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.repository.MealRepository;

import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    @Autowired
    private CrudMealRepository repository;

    @Override
    public Meal save(Meal meal, int id) {
        if (!meal.isNew()) {

        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Meal get(int id, int menuId) {
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return repository.findAll();
    }
}
