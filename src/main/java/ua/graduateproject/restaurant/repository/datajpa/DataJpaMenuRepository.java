package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import ua.graduateproject.restaurant.model.Menu;
import ua.graduateproject.restaurant.repository.MenuRepository;

import java.util.List;

public class DataJpaMenuRepository implements MenuRepository {

    @Autowired
    private MenuRepository repository;

    @Override
    public Menu get(int restaurantId, int userId) {
        return null;
    }

    @Override
    public Menu save(Menu menu, int restaurantId, int userId) {
        if (!menu.isNew() && get(restaurantId, userId) == null) {
            return null;
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public List<Menu> getAllByUser(int userId) {
        return null;
    }
}
