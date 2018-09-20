package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.graduateproject.restaurant.model.Restaurant;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id AND r.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Query("SELECT r FROM Restaurant r WHERE r.address=:address")
    Restaurant getByAddress(@Param("address") String address);

    @Query("SELECT r FROM Restaurant r WHERE r.id =?1 AND r.user.id=?2")
    Restaurant getByUser(int id, int userId);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menu WHERE r.id=?1")
    Restaurant getWithMeals(int id);

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.user WHERE r.id=?1")
    Restaurant getWithUser(int id);
}
