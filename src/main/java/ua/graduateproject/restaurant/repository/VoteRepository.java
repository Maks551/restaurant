package ua.graduateproject.restaurant.repository;

import ua.graduateproject.restaurant.model.Vote;

public interface VoteRepository {

    Vote get(int restaurantId, int userId);

    int getCount(int restaurantId);

    Vote save(Vote vote ,int restaurantId, int userId);

    boolean delete(int restaurantId, int userId);
}
