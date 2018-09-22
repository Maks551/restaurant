package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.graduateproject.restaurant.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Vote save(Vote entity);

    @Query("SELECT v FROM Vote v WHERE v.id=?1 AND v.userId=?2")
    Vote getByUser(int id, int userId);

    @Query("SELECT v FROM Vote v WHERE v.restaurantId=:restaurantId")
    List<Vote> getAllByRestaurant(@Param("restaurantId") int restaurantId);

    @Query("SELECT COUNT (v) FROM Vote v WHERE v.restaurantId=:restaurantId")
    int getAllCountByRestaurant(@Param("restaurantId") int restaurantId);

    @Query("SELECT SUM (v.vote) FROM Vote v WHERE v.restaurantId=:restaurantId")
    int getAllPositiveCountByRestaurant(@Param("restaurantId") int restaurantId);
}
