package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.repository.MealRepository;

import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    @Autowired
    private CrudMealRepository crudMealRepo;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepo;

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepo.getOne(restaurantId));
        return crudMealRepo.save(meal);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMealRepo.delete(id, restaurantId) != 0;
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return crudMealRepo.getAll(restaurantId);
    }

    @Override
    public Meal get(int id, int restaurantId) {
        return crudMealRepo.findById(id).filter(meal -> meal.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public Meal getWithRestaurant(int id, int restaurantId) {
        return crudMealRepo.getWithRestaurant(id, restaurantId);
    }
}
