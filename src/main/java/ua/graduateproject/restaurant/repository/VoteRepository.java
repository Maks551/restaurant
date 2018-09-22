package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {

    Vote get(int id);

    Vote getByUser(int id, int userId);

    List<Vote> getAllByRestaurant(int restaurantId);

    int getAllCountByRestaurant(int restaurantId);

    int getAllPositiveCountByRestaurant(int restaurantId);

    Vote save(Vote vote);

    boolean delete(int id);
}
