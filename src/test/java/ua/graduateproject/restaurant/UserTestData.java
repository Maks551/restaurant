package ua.graduateproject.restaurant;

import ua.graduateproject.restaurant.model.Role;
import ua.graduateproject.restaurant.model.User;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.graduateproject.restaurant.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int USER2_ID = USER_ID + 1;
    public static final int USER3_ID = USER_ID + 2;
    public static final int USER4_ID = USER_ID + 3;
    public static final int ADMIN_ID = USER_ID + 4;
    public static final int ADMIN2_ID = USER_ID + 5;

    public static final String USER_EMAIL = "user1@gmail.com";

    public static final User USER_1 = new User(USER_ID, "User1", "user1@gmail.com", "password", Role.ROLE_USER);
    public static final User USER_2 = new User(USER2_ID, "User2", "user2@gmail.com", "password", Role.ROLE_USER);
    public static final User USER_3 = new User(USER3_ID, "User3", "user3@gmail.com", "password", Role.ROLE_USER);
    public static final User USER_4 = new User(USER4_ID, "User4", "user4@gmail.com", "password", Role.ROLE_USER);
    public static final User ADMIN_1 = new User(ADMIN_ID, "Admin1", "admin1@gmail.com", "user", Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User ADMIN_2 = new User(ADMIN2_ID, "Admin2", "admin2@gmail.com", "user", Role.ROLE_USER, Role.ROLE_ADMIN);

    public static final List<User> USERS = Arrays.asList(ADMIN_1, ADMIN_2, USER_1, USER_2, USER_3, USER_4);

    public static User getCreatedUser() {
        return new User(null, "Created user", "created@gmail.com", "created", Role.ROLE_USER);
    }

    public static User getCreatedDuplicateEmail() {
        return new User(null, "Created user", "user1@gmail.com", "created", Role.ROLE_USER);
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurants", "registered", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurants", "registered", "password").isEqualTo(expected);
    }
}
