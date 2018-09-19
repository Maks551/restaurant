package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ua.graduateproject.restaurant.model.User;
import ua.graduateproject.restaurant.repository.UserRepository;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private CrudUserRepository crudUserRepo;

    @Override
    public User get(int id) {
        return crudUserRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepo.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepo.getByEmail(email);
    }

    @Override
    public User save(User user) {
        return crudUserRepo.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudUserRepo.delete(id) != 0;
    }
}
