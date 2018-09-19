package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {

    Vote get(int id);

    Vote getByUser(int id, int userId);

    Vote getByRestaurant(int id, int restaurantId);

    List<Vote> getAllByRestaurant(int restaurantId);

    int getAllCountByRestaurant(int restaurantId);

    Vote save(Vote vote, int restaurantId, int userId);

    boolean delete(int id, int restaurantId, int userId);
}
