package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.graduateproject.restaurant.model.Menu;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
}
