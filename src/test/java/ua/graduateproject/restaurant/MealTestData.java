package ua.graduateproject.restaurant;

import ua.graduateproject.restaurant.model.Meal;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.graduateproject.restaurant.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int RESTAURANT1_MEAL_ID = START_SEQ + 11;
    public static final int RESTAURANT2_MEAL_ID = RESTAURANT1_MEAL_ID + 4;
    public static final int RESTAURANT3_MEAL_ID = RESTAURANT1_MEAL_ID + 7;

    public static final Meal RESTAURANT1_MEAL1 = new Meal(RESTAURANT1_MEAL_ID, "Борщ", 600);
    public static final Meal RESTAURANT1_MEAL2 = new Meal(RESTAURANT1_MEAL_ID + 1, "Суп", 500);
    public static final Meal RESTAURANT1_MEAL3 = new Meal(RESTAURANT1_MEAL_ID + 2, "Салат", 300);
    public static final Meal RESTAURANT1_MEAL4 = new Meal(RESTAURANT1_MEAL_ID + 3, "Гарнір", 800);
    public static final Meal RESTAURANT2_MEAL1 = new Meal(RESTAURANT2_MEAL_ID, "Пиріг", 600);
    public static final Meal RESTAURANT2_MEAL2 = new Meal(RESTAURANT2_MEAL_ID + 1, "Кекс", 400);
    public static final Meal RESTAURANT2_MEAL3 = new Meal(RESTAURANT2_MEAL_ID + 2, "Торт", 700);
    public static final Meal RESTAURANT3_MEAL1 = new Meal(RESTAURANT3_MEAL_ID, "Біфштекс", 1000);
    public static final Meal RESTAURANT3_MEAL2 = new Meal(RESTAURANT3_MEAL_ID + 1, "Пельмені", 900);
    public static final Meal RESTAURANT3_MEAL3 = new Meal(RESTAURANT3_MEAL_ID + 2, "Шашлик", 1000);

    public static final List<Meal> RESTAURANT1_MEALS = Arrays.asList(RESTAURANT1_MEAL1, RESTAURANT1_MEAL2, RESTAURANT1_MEAL3, RESTAURANT1_MEAL4);

    public static Meal getCreated() {
        return new Meal(null, "Created description", 500);
    }

    public static Meal getUpdated() {
        return new Meal(RESTAURANT1_MEAL_ID, "Updated description", 500);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
