package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote get(int id, int restaurantId);

    List<Vote> getAll(int restaurant);

    int getCount(int restaurantId);

    Vote save(Vote vote, int restaurantId, int userId);

    boolean delete(int id, int restaurantId, int userId);
}
