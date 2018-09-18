package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.graduateproject.restaurant.model.Menu;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.getId()=:restaurantId")
    boolean delete(@Param("id") int id, @Param("restaurantId") int restaurantId);
}
