package ua.graduateproject.restaurant.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ua.graduateproject.restaurant.service.MealService;
import ua.graduateproject.restaurant.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.graduateproject.restaurant.MealTestData.RESTAURANT1_MEAL1;
import static ua.graduateproject.restaurant.MealTestData.RESTAURANT1_MEALS;
import static ua.graduateproject.restaurant.MealTestData.RESTAURANT1_MEAL_ID;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT_ID;
import static ua.graduateproject.restaurant.TestUtil.contentJson;
import static ua.graduateproject.restaurant.TestUtil.userHttpBasic;
import static ua.graduateproject.restaurant.UserTestData.ADMIN_1;
import static ua.graduateproject.restaurant.UserTestData.USER_1;

class MealProfileRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealProfileRestController.REST_URL + '/';

    @Autowired
    private MealService service;

    @Test
    void testGet() throws Exception{
        mockMvc.perform(get(REST_URL + RESTAURANT1_MEAL_ID + "/restaurant-id/" + RESTAURANT_ID)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1_MEAL1));
    }

    @Test
    void testGetAll() throws Exception{
        mockMvc.perform(get(REST_URL + "restaurant-id/" + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1_MEALS));
    }
}