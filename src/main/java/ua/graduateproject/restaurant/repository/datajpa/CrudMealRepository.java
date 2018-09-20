package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.graduateproject.restaurant.model.Meal;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    @Transactional
    Meal save(Meal entity);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId ORDER BY m.restaurant.id")
    List<Meal> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Meal m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    Meal getWithRestaurant(int id, int restaurantId);
}
