package ua.graduateproject.restaurant.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ua.graduateproject.restaurant.model.Restaurant;
import ua.graduateproject.restaurant.service.RestaurantService;
import ua.graduateproject.restaurant.web.AbstractControllerTest;
import ua.graduateproject.restaurant.web.SecurityUtil;
import ua.graduateproject.restaurant.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.graduateproject.restaurant.RestaurantTestData.*;
import static ua.graduateproject.restaurant.TestUtil.contentJson;
import static ua.graduateproject.restaurant.TestUtil.readFromJson;
import static ua.graduateproject.restaurant.UserTestData.ADMIN_ID;

public class RestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    private RestaurantService service;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_1));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_ID))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + "all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_LIST));
    }

    @Test
    void testUpdated() throws Exception {
        Restaurant updated = getUpdatedByAdmin_1();
        SecurityUtil.setAuthUserId(ADMIN_ID);

        mockMvc.perform(put(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(service.get(RESTAURANT_ID), updated);
    }

    @Test
    void testCreated() throws Exception {
        Restaurant created = getCreated();
        SecurityUtil.setAuthUserId(ADMIN_ID);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))).andDo(print());

        Restaurant returned = readFromJson(action, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(), created, RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    }
}
