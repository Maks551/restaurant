package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    private static final Sort SORT_NAME_ADDRESS = new Sort(Sort.Direction.ASC, "name", "address");

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepo;

    @Autowired
    private CrudUserRepository crudUserRepo;

    @Override
    public Restaurant get(int id, int userId) {
        return crudRestaurantRepo.get(id, userId);
    }

    @Override
    public Restaurant getByAddress(String address) {
        return crudRestaurantRepo.getByAddress(address);
    }

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        if (!restaurant.isNew() && get(restaurant.getId(), userId) == null) {
            return null;
        }
        restaurant.setUser(crudUserRepo.getOne(userId));
        return crudRestaurantRepo.save(restaurant);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRestaurantRepo.delete(id, userId) != 0;
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepo.findAll(SORT_NAME_ADDRESS);
    }

    @Override
    public Restaurant getWithMenu(int id) {
        return crudRestaurantRepo.getWithMeals(id);
    }

    @Override
    public Restaurant getWithUser(int id, int userId) {
        return crudRestaurantRepo.getWithUser(id, userId);
    }
}
