package ua.graduateproject.restaurant.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.service.RestaurantService;
import ua.graduateproject.restaurant.web.AbstractControllerTest;
import ua.graduateproject.restaurant.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.graduateproject.restaurant.RestaurantTestData.*;
import static ua.graduateproject.restaurant.TestUtil.contentJson;
import static ua.graduateproject.restaurant.TestUtil.readFromJson;
import static ua.graduateproject.restaurant.TestUtil.userHttpBasic;
import static ua.graduateproject.restaurant.UserTestData.ADMIN_1;
import static ua.graduateproject.restaurant.UserTestData.USER_1;

public class RestaurantAdminRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantAdminRestController.REST_URL + '/';

    @Autowired
    private RestaurantService service;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_1));
    }

    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + "all")
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_LIST));
    }

    @Test
    void testUpdated() throws Exception {
        Restaurant updated = getUpdatedByAdmin_1();

        mockMvc.perform(put(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_1))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(service.get(RESTAURANT_ID), updated);
    }

    @Test
    void testCreated() throws Exception {
        Restaurant created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_1))
                .content(JsonUtil.writeValue(created))).andDo(print());

        Restaurant returned = readFromJson(action, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(), created, RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    }
}
