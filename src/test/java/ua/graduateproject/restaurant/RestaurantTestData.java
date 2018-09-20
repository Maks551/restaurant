package ua.graduateproject.restaurant;

import ua.graduateproject.restaurant.model.Restaurant;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.graduateproject.restaurant.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 4;
    public static final int RESTAURANT2_ID = RESTAURANT1_ID + 1;
    public static final int RESTAURANT3_ID = RESTAURANT1_ID + 2;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Ресторан 1",
            LocalDateTime.of(2018, 9, 20, 10, 10), "м. Київ вул. 1");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "menu");
    }
}
