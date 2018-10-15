package ua.graduateproject.restaurant.service;

import ua.graduateproject.restaurant.model.Vote;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

public interface VoteService {

    Vote create(Vote vote);

    void update(Vote vote);

    void delete(int id);

    Vote get(int id);

    Vote getByUser(int id, int userId);

    List<Vote> getAll(int restaurantId);

    int getAllCountByRestaurant(int restaurantId);

    int getAllPositiveCountByRestaurant(int restaurantId);
}
