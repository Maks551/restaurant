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
    public static final int RESTAURANT1_ID = START_SEQ + 4;
    public static final int RESTAURANT2_ID = RESTAURANT1_ID + 1;
    public static final int RESTAURANT3_ID = RESTAURANT1_ID + 2;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT1_ID, "Ресторан 1",
            LocalDateTime.of(2018, 9, 20, 10, 10), "м. Київ вул. 1", ADMIN_1);
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT2_ID, "Ресторан 2",
            LocalDateTime.of(2018, 9, 20, 9, 0), "м. Київ вул. 2", ADMIN_1);
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT3_ID, "Ресторан 3",
            LocalDateTime.of(2018, 9, 20, 9, 30), "м. Київ вул. 3", ADMIN_2);

    public static final List<Restaurant> RESTAURANT_LIST = Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);

    public static final String ADDRESS_R1 = "м. Київ вул. 1";

    public static Restaurant getCreated() {
        return new Restaurant(null, "Created restaurant",
                LocalDateTime.of(2018, 9, 22, 10, 10), "м. Київ вул. 4", ADMIN_1);
    }

    public static Restaurant getUpdatedByAdmin_1() {
        return new Restaurant(RESTAURANT1_ID, "Updated restaurant",
                LocalDateTime.of(2018, 9, 22, 10, 10), "м. Київ вул. 1", ADMIN_1);
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
