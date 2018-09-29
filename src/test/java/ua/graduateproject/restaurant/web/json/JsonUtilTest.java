package ua.graduateproject.restaurant.web.json;

import org.junit.jupiter.api.Test;
import ua.graduateproject.restaurant.UserTestData;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.model.User;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void testWriteOnlyAccess() throws Exception {
        String json = JsonUtil.writeValue(UserTestData.USER_1);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.USER_1, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}