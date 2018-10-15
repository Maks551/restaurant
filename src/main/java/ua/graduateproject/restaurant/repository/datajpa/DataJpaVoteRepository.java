package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.graduateproject.restaurant.model.Vote;
import ua.graduateproject.restaurant.repository.VoteRepository;
import java.time.LocalTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    private static final int LAST_TIME_REVOTING = 11;

    @Autowired
    private CrudVoteRepository crudVoteRepo;

    @Override
    public Vote get(int id){
        return crudVoteRepo.findById(id).orElse(null);
    }

    @Override
    public Vote getByUser(int id, int userId) {
        return crudVoteRepo.getByUser(id, userId);
    }

    @Override
    public List<Vote> getAllByRestaurant(int restaurantId) {
        return crudVoteRepo.getAllByRestaurant(restaurantId);
    }

    @Override
    public int getAllCountByRestaurant(int restaurantId) {
        return crudVoteRepo.getAllCountByRestaurant(restaurantId);
    }

    @Override
    public int getAllPositiveCountByRestaurant(int restaurantId) {
        return crudVoteRepo.getAllPositiveCountByRestaurant(restaurantId);
    }

    @Override
    public Vote save(Vote vote) {
        if (!vote.isNew() && vote.getDateTime().toLocalTime().isAfter(LocalTime.of(LAST_TIME_REVOTING, 0))){
            return null;
        }
        if (vote.getVote() > 0) {
            vote.setVote(1);
        } else {
            vote.setVote(-1);
        }
        return crudVoteRepo.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return crudVoteRepo.delete(id) != 0;
    }
}
