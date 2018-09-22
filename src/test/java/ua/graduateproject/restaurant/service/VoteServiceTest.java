package ua.graduateproject.restaurant.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ua.graduateproject.restaurant.model.Vote;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT_ID;
import static ua.graduateproject.restaurant.UserTestData.ADMIN_ID;
import static ua.graduateproject.restaurant.UserTestData.USER_ID;
import static ua.graduateproject.restaurant.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void create() {
        Vote newVote = new Vote(null, ADMIN_ID, RESTAURANT_ID, LocalDateTime.of(2018, 9, 21, 10, 0), 1);
        Vote created = service.create(newVote);
        assertMatch(service.getAll(RESTAURANT_ID), VOTE_1, VOTE_2, VOTE_3, VOTE_4, created);
    }

    @Test
    void duplicateCreate() {
        Vote newVote = new Vote(USER_ID, RESTAURANT_ID, LocalDateTime.of(2018, 9, 20, 12, 0), 1);
        assertThrows(DataAccessException.class, () -> service.create(newVote));
    }

    @Test
    void update() {
        Vote vote = new Vote(VOTE_1);
        vote.setDateTime(LocalDateTime.of(2018, 9, 21, 10, 30));
        vote.setVote(-1);
        service.update(new Vote(vote));
        assertMatch(service.get(vote.getId()), vote);
    }

    @Test
    void updateRepeat() {
        Vote vote = new Vote(VOTE_1);
        vote.setDateTime(LocalDateTime.of(2018, 9, 21, 11, 30));
        vote.setVote(-1);
        assertThrows(NotFoundException.class, () -> service.update(new Vote(vote)));
    }

    @Test
    void delete() {
        service.delete(VOTE_ID);
        assertMatch(service.getAll(RESTAURANT_ID), VOTE_2, VOTE_3, VOTE_4);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    void get() {
        assertMatch(service.get(VOTE_ID), VOTE_1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    void getByUser() {
        Vote byUser = service.getByUser(VOTE_ID, USER_ID);
        assertMatch(byUser, VOTE_1);
        Assertions.assertThat(byUser.getUserId()).isEqualTo(USER_ID);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(RESTAURANT_ID), VOTE_1, VOTE_2, VOTE_3, VOTE_4);
    }

    @Test
    void getAllCountByRestaurant() {
        int count = service.getAllCountByRestaurant(RESTAURANT_ID);
        Assertions.assertThat(count).isEqualTo(COUNT_BY_RESTAURANT1);
    }

    @Test
    void getAllPositiveCountByRestaurant() {
        int count = service.getAllPositiveCountByRestaurant(RESTAURANT_ID);
        Assertions.assertThat(count).isEqualTo(COUNT_POSITIVE_BY_RESTAURANT1);
    }

    @Test
    void testValidation() {
        validateRootCause(() -> service.create(new Vote(null, null, RESTAURANT_ID, LocalDateTime.of(2018, 9, 20, 10, 10), 1)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Vote(null, USER_ID, null, LocalDateTime.of(2018, 9, 20, 10, 10), 1)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Vote(null, USER_ID, RESTAURANT_ID, null, 1)), ConstraintViolationException.class);
    }
}