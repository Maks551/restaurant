package ua.graduateproject.restaurant;

import ua.graduateproject.restaurant.model.Restaurant;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.graduateproject.restaurant.UserTestData.ADMIN_1;
import static ua.graduateproject.restaurant.UserTestData.ADMIN_2;
import static ua.graduateproject.restaurant.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT_ID = START_SEQ + 6;
    public static final int RESTAURANT2_ID = RESTAURANT_ID + 1;
    public static final int RESTAURANT3_ID = RESTAURANT_ID + 2;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID, "Restaurant 1",
            LocalDateTime.of(2018, 9, 20, 10, 10), "Kiev str. 1");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID + 1, "Restaurant 2",
            LocalDateTime.of(2018, 9, 20, 9, 0), "Kiev str. 2");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID + 2, "Restaurant 3",
            LocalDateTime.of(2018, 9, 20, 9, 30), "Kiev str. 3");

    public static final List<Restaurant> RESTAURANT_LIST = Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);

    public static final String ADDRESS_R1 = "Kiev str. 1";

    public static Restaurant getCreated() {
        return new Restaurant(null, "Created restaurant",
                LocalDateTime.of(2018, 9, 22, 10, 10), "м. Київ вул. 4");
    }

    public static Restaurant getUpdatedByAdmin_1() {
        Restaurant updated = new Restaurant(RESTAURANT_1);
        updated.setAddress("New address");
        updated.setName("New name");
        return updated;
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "menu");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "menu").isEqualTo(expected);
    }
}
