package ua.graduateproject.restaurant;

import ua.graduateproject.restaurant.model.Vote;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT2_ID;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT3_ID;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT_ID;
import static ua.graduateproject.restaurant.UserTestData.*;
import static ua.graduateproject.restaurant.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE_ID = START_SEQ + 9;

    public static final Vote VOTE_1 = new Vote(VOTE_ID, USER_ID, RESTAURANT_ID,
            LocalDateTime.of(2018, 9, 20, 10, 10), 1);
    public static final Vote VOTE_2 = new Vote(VOTE_ID + 1, USER2_ID, RESTAURANT_ID,
            LocalDateTime.of(2018, 9, 20, 10, 20), 1);
    public static final Vote VOTE_3 = new Vote(VOTE_ID + 2, USER4_ID, RESTAURANT_ID,
            LocalDateTime.of(2018, 9, 20, 11, 30), -1);
    public static final Vote VOTE_4 = new Vote(VOTE_ID + 3, USER_ID, RESTAURANT_ID,
            LocalDateTime.of(2018, 9, 19, 10, 0), 1);

    public static final List<Vote> RESTAURANT1_VOTES = Arrays.asList(VOTE_1, VOTE_2, VOTE_3, VOTE_4);

    public static final int COUNT_BY_RESTAURANT1 = 4;
    public static final int COUNT_POSITIVE_BY_RESTAURANT1 = 2;

    public static Vote getUpdated() {
        return new Vote(VOTE_1.getId(), VOTE_1.getUserId(), VOTE_1.getRestaurantId(),
                LocalDateTime.of(2018, 9, 21, 10, 30), -1);
    }

    public static Vote getCreated() {
        return new Vote(null, ADMIN_ID, RESTAURANT_ID,
                LocalDateTime.of(2018, 9, 21, 10, 0), 1);
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
