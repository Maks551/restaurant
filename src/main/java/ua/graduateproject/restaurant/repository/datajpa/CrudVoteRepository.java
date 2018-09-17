package ua.graduateproject.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.graduateproject.restaurant.model.Vote;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

}
