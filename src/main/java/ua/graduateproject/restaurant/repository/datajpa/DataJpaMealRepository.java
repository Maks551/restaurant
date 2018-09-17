package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.stereotype.Repository;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.repository.MealRepository;

import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {


    @Override
    public Meal save(Meal meal, int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Meal get(int id) {
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return null;
    }
}
