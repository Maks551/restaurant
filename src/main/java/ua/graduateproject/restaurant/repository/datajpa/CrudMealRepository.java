package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.graduateproject.restaurant.model.Meal;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.menu.id=:menuId")
    int delete(@Param("id") int id, @Param("menuId") int menuId);

    @Override
    @Transactional
    Meal save(Meal entity);

    @Query("SELECT m FROM Meal m WHERE m.menu.id=:menuId ORDER BY m.description")
    List<Meal> getAll(@Param("menuId") int menuId);
}
