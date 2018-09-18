package ua.graduateproject.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.repository.MealRepository;
import ua.graduateproject.restaurant.util.ValidationUtil;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

import static ua.graduateproject.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id, int menuId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, menuId), id);
    }

    @Override
    public void delete(int id, int menuId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, menuId), id);
    }

    @Override
    public List<Meal> getAll(int menuId) {
        return repository.getAll(menuId);
    }

    @Override
    public void update(Meal meal, int menuId) throws NotFoundException {
        checkNotFoundWithId(repository.save(meal, menuId), menuId);
    }

    @Override
    public Meal create(Meal meal, int menuId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, menuId);
    }
}
