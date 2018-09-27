package ua.graduateproject.restaurant.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ua.graduateproject.restaurant.model.Role;
import ua.graduateproject.restaurant.model.User;
import ua.graduateproject.restaurant.service.UserService;
import ua.graduateproject.restaurant.web.AbstractControllerTest;
import ua.graduateproject.restaurant.web.SecurityUtil;
import ua.graduateproject.restaurant.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.graduateproject.restaurant.TestUtil.contentJson;
import static ua.graduateproject.restaurant.UserTestData.*;

public class ProfileRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileRestController.REST_URL + '/';

    @Autowired
    private UserService service;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER_1));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), ADMIN_1, ADMIN_2, USER_2, USER_3, USER_4);
    }

    @Test
    void testUpdate() throws Exception {
        User updated = new User(USER_ID, "New name", "new.email@gmail.com", "new.password", Role.ROLE_USER);
        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(service.get(USER_ID), updated);
    }
}
