package com.lreebom.springbootlearn.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getByIdFailWhenIdLessThanOne() throws Exception {
        mockMvc.perform(get("/users/getById").param("id", "0")).andExpect(status().isOk()).andExpect(jsonPath("$.code").value(-1)).andExpect(jsonPath("$.message").value("用户ID必须大于0"));
    }

    @Test
    void createFailWhenEmailInvalid() throws Exception {
        String json = """
                {
                  "username": "mock_user",
                  "email": "bad-email"
                }
                """;

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(-1))
                .andExpect(jsonPath("$.message").value("邮箱格式不正确"));
    }
}
