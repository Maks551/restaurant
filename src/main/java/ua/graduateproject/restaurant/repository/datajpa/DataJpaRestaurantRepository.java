package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.model.Role;
import ua.graduateproject.restaurant.model.User;
import ua.graduateproject.restaurant.repository.RestaurantRepository;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    private static final Sort SORT_NAME_ADDRESS = new Sort(Sort.Direction.ASC, "name", "address");

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepo;

    @Autowired
    private CrudUserRepository crudUserRepo;

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepo.findById(id).orElse(null);
    }

    @Override
    public Restaurant getByUser(int id, int userId) {
        return crudRestaurantRepo.getByUser(id, userId);
    }

    @Override
    public Restaurant getByAddress(String address) {
        return crudRestaurantRepo.getByAddress(address);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant, int userId) {
        User user = crudUserRepo.findById(userId).orElse(null);
        if (user == null || !user.getRoles().contains(Role.ROLE_ADMIN)) {
            throw new NotFoundException("user mast be admin");
        }
        if (restaurant.getUser() != null && restaurant.getUser().getId() != userId){
            return null;
        }
        restaurant.setUser(user);
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
    public Restaurant getWithUser(int id) {
        return crudRestaurantRepo.getWithUser(id);
    }
}
