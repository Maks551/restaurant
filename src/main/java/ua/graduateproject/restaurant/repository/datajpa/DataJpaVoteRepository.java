package ua.graduateproject.restaurant.repository.datajpa;

import ua.graduateproject.restaurant.model.Vote;
import ua.graduateproject.restaurant.repository.VoteRepository;

public class DataJpaVoteRepository implements VoteRepository {

    @Override
    public Vote get(int restaurantId, int userId) {
        return null;
    }

    @Override
    public int getCount(int restaurantId) {
        return 0;
    }

    @Override
    public Vote save(Vote vote, int restaurantId, int userId) {
        return null;
    }

    @Override
    public boolean delete(int restaurantId, int userId) {
        return false;
    }
}
