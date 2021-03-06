package ua.graduateproject.restaurant;

import org.springframework.test.web.servlet.ResultMatcher;
import ua.graduateproject.restaurant.model.Role;
import ua.graduateproject.restaurant.model.User;
import ua.graduateproject.restaurant.web.json.JsonUtil;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ua.graduateproject.restaurant.model.AbstractBaseEntity.START_SEQ;
import static ua.graduateproject.restaurant.web.json.JsonUtil.writeIgnoreProps;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    static final int USER2_ID = USER_ID + 1;
    private static final int USER3_ID = USER_ID + 2;
    static final int USER4_ID = USER_ID + 3;
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
        return new User(null, "Created user", "created@gmail.com", "newPassword", Role.ROLE_USER, Role.ROLE_ADMIN);
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

    public static String jsonWithPassword(User user, String pass) {
        return JsonUtil.writeAdditionProps(user, "password", pass);
    }

    public static ResultMatcher contentJson(User... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "registered", "password"));
    }

    public static ResultMatcher contentJson(User expected) {
        return content().json(writeIgnoreProps(expected, "registered", "password"));
    }
}
