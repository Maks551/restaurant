package ua.graduateproject.restaurant.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.service.MealService;
import ua.graduateproject.restaurant.web.AbstractControllerTest;
import ua.graduateproject.restaurant.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.graduateproject.restaurant.MealTestData.*;
import static ua.graduateproject.restaurant.MealTestData.RESTAURANT1_MEAL3;
import static ua.graduateproject.restaurant.MealTestData.RESTAURANT1_MEAL4;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT_ID;
import static ua.graduateproject.restaurant.TestUtil.contentJson;
import static ua.graduateproject.restaurant.TestUtil.readFromJson;
import static ua.graduateproject.restaurant.TestUtil.userHttpBasic;
import static ua.graduateproject.restaurant.UserTestData.ADMIN_1;
import static ua.graduateproject.restaurant.UserTestData.USER_1;

class MealAdminRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealAdminRestController.REST_URL + '/';

    @Autowired
    private MealService service;

    @Test
    void testGet() throws Exception{
        mockMvc.perform(get(REST_URL + RESTAURANT1_MEAL_ID + "/restaurant-id/" + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1_MEAL1));
    }

    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_MEAL_ID + "/restaurant-id/" + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(RESTAURANT_ID), RESTAURANT1_MEAL2, RESTAURANT1_MEAL3, RESTAURANT1_MEAL4);
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

    @Test
    void testUpdate() throws Exception{
        Meal updated = getUpdated();

        mockMvc.perform(put(REST_URL + RESTAURANT1_MEAL_ID + "/restaurant-id/" + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_1))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(service.get(RESTAURANT1_MEAL_ID, RESTAURANT_ID), updated);
    }

    @Test
    void testCreate() throws Exception {
        Meal created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + "restaurant-id/" + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_1))
                .content(JsonUtil.writeValue(created))).andDo(print());

        Meal returned = readFromJson(action, Meal.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(RESTAURANT_ID), RESTAURANT1_MEAL1, RESTAURANT1_MEAL2, RESTAURANT1_MEAL3, RESTAURANT1_MEAL4, created);
    }
}