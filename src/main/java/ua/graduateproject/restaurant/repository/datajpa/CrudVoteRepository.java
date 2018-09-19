package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.graduateproject.restaurant.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.restaurantId=:restaurantId AND v.userId=:userId")
    int delete(int id, int restaurantId, int userId);

    @Override
    @Transactional
    Vote save(Vote entity);

    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.userId=:userId")
    Vote getByUser(int id, int userId);

    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.restaurantId=:restaurantId")
    Vote getByRestaurant(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.restaurantId=:restaurantId")
    List<Vote> getAllByRestaurant(@Param("restaurantId") int restaurantId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.restaurantId=:restaurantId AND v.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Vote> getBetween(LocalDateTime startDate, LocalDateTime endDate, int restaurantId);

    @Query("SELECT COUNT (v) FROM Vote v WHERE v.restaurantId=:restaurantId")
    int getAllCountByRestaurant(@Param("restaurantId") int restaurantId);

//    @SuppressWarnings("JpaQlInspection")
//    @Query("SELECT m from Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
//    List<Vote> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

}
