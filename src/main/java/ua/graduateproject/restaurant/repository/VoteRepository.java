package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {

    Vote getByUser(int id, int userId);

    Vote getByRestaurant(int id, int restaurantId);

    Vote getByDateTime(LocalDateTime dateTime);

    List<Vote> getAllByRestaurant(int restaurantId);

    List<Vote> getAllByDateTimeAndRestaurant(LocalDateTime dateTime, int restaurantId);

    int getAllCountByRestaurant(int restaurantId);

    int getCountByDay(int restaurantId);

    Vote save(Vote vote, int restaurantId, int userId);

    boolean delete(int id, int restaurantId, int userId);
}
