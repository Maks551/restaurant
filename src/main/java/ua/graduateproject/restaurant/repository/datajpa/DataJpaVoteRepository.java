package ua.graduateproject.restaurant.repository.datajpa;

import ua.graduateproject.restaurant.model.Vote;
import ua.graduateproject.restaurant.repository.VoteRepository;

import java.time.LocalDateTime;
import java.util.List;

public class DataJpaVoteRepository implements VoteRepository {

    @Override
    public Vote getByUser(int id, int userId) {
        return null;
    }

    @Override
    public Vote getByRestaurant(int id, int restaurantId) {
        return null;
    }

    @Override
    public Vote getByDateTime(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public List<Vote> getAllByRestaurant(int restaurantId) {
        return null;
    }

    @Override
    public List<Vote> getAllByDateTimeAndRestaurant(LocalDateTime dateTime, int restaurantId) {
        return null;
    }

    @Override
    public int getAllCountByRestaurant(int restaurantId) {
        return 0;
    }

    @Override
    public int getCountByDay(int restaurantId) {
        return 0;
    }

    @Override
    public Vote save(Vote vote, int restaurantId, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int restaurantId, int userId) {
        return false;
    }
}
