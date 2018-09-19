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
    public Meal save(Meal meal, int menuId) {
        if (!meal.isNew() && get(meal.getId(), menuId) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepo.getOne(menuId));
        return crudMealRepo.save(meal);
    }

    @Override
    public boolean delete(int id, int menuId) {
        return crudMealRepo.delete(id, menuId) != 0;
    }

    @Override
    public List<Meal> getAll(int menuId) {
        return crudMealRepo.getAll(menuId);
    }

    @Override
    public Meal get(int id, int menuId) {
        return crudMealRepo.findById(id).filter(meal -> meal.getRestaurant().getId() == menuId).orElse(null);
    }
}
