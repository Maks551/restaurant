package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.graduateproject.restaurant.model.User;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
}
