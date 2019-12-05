package com.trcklst.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trcklst.register.mock.RegisterInMock;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RegisterApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @PostConstruct
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void insertTest() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                    .content(objectMapper.writeValueAsString(RegisterInMock.REGISTER_IN))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONObject jsonObject = new JSONObject(response);
        Assertions.assertEquals(0, jsonObject.getInt("id"));
        Assertions.assertEquals("user@gmail.com", jsonObject.getString("username"));
    }

}
