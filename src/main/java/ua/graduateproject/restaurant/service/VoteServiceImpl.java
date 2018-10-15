package ua.graduateproject.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.graduateproject.restaurant.model.Vote;
import ua.graduateproject.restaurant.repository.VoteRepository;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

import static ua.graduateproject.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote mast not be null");
        return repository.save(vote);
    }

    @Override
    public void update(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote), vote.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Vote get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Vote getByUser(int id, int userId) {
        return checkNotFoundWithId(repository.getByUser(id, userId), id);
    }

    @Override
    public List<Vote> getAll(int restaurantId) {
        return repository.getAllByRestaurant(restaurantId);
    }

    @Override
    public int getAllCountByRestaurant(int restaurantId) {
        return repository.getAllCountByRestaurant(restaurantId);
    }

    @Override
    public int getAllPositiveCountByRestaurant(int restaurantId) {
        return repository.getAllPositiveCountByRestaurant(restaurantId);
    }
}
