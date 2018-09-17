package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.graduateproject.restaurant.model.Restaurant;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
