package ua.graduateproject.restaurant.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ua.graduateproject.restaurant.model.Vote;
import ua.graduateproject.restaurant.service.VoteService;
import ua.graduateproject.restaurant.web.AbstractControllerTest;
import ua.graduateproject.restaurant.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT_ID;
import static ua.graduateproject.restaurant.TestUtil.contentJson;
import static ua.graduateproject.restaurant.TestUtil.readFromJson;
import static ua.graduateproject.restaurant.TestUtil.userHttpBasic;
import static ua.graduateproject.restaurant.UserTestData.ADMIN_1;
import static ua.graduateproject.restaurant.UserTestData.USER_1;
import static ua.graduateproject.restaurant.VoteTestData.*;

public class VoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + VOTE_ID)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(VOTE_1));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + VOTE_ID)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(RESTAURANT_ID), VOTE_2, VOTE_3, VOTE_4);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + "restaurant-id/" + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1_VOTES));
    }

    @Test
    void testUpdate() throws Exception {
        Vote updated = getUpdated();

        mockMvc.perform(put(REST_URL + VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER_1))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(service.get(VOTE_ID), updated);
    }

    @Test
    void testCreated() throws Exception {
        Vote created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER_1))
                .content(JsonUtil.writeValue(created))).andDo(print());

        Vote returned = readFromJson(action, Vote.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(RESTAURANT_ID), VOTE_1, VOTE_2, VOTE_3, VOTE_4, created);
    }
}
