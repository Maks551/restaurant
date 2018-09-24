package ua.graduateproject.restaurant.web.json;

import org.junit.jupiter.api.Test;
import ua.graduateproject.restaurant.model.Meal;

import java.util.List;

import static ua.graduateproject.restaurant.MealTestData.RESTAURANT1_MEAL1;
import static ua.graduateproject.restaurant.MealTestData.RESTAURANT1_MEALS;
import static ua.graduateproject.restaurant.MealTestData.assertMatch;

class JsonUtilTest {

    @Test
    void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(RESTAURANT1_MEAL1);
        System.out.println(json);
        Meal meal = JsonUtil.readValue(json, Meal.class);
        assertMatch(meal, RESTAURANT1_MEAL1);
    }

    @Test
    void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(RESTAURANT1_MEALS);
        System.out.println(json);
        List<Meal> meals = JsonUtil.readValues(json, Meal.class);
        assertMatch(meals, RESTAURANT1_MEALS);
    }
}