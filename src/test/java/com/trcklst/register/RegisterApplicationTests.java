package com.trcklst.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trcklst.register.core.db.UserRepository;
import com.trcklst.register.core.dto.RegisterIn;
import com.trcklst.register.mock.UserMock;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RegisterApplicationTests {

    private static final String REGISTER_URI = "/api/register";

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private UserRepository userRepository;

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
        String response = sendRegister(RegisterInMock.REGISTER_IN)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONObject jsonObject = new JSONObject(response);
        Assertions.assertEquals(1, jsonObject.getInt("id"));
        Assertions.assertEquals("user@gmail.com", jsonObject.getString("username"));
    }

    @Test
    void emptyUsername() throws Exception {
        RegisterIn registerInWithoutUsername = RegisterInMock.REGISTER_IN.toBuilder()
                .username("")
                .build();
        sendRegister(registerInWithoutUsername).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void existingUsername() throws Exception {
        userRepository.save(UserMock.VALID_USER);
        String existingUsername = "user";
        RegisterIn registerInWithExistingUsername = RegisterInMock.REGISTER_IN.toBuilder()
                .username(existingUsername)
                .build();
        sendRegister(registerInWithExistingUsername).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private ResultActions sendRegister(RegisterIn registerIn) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(REGISTER_URI)
                .content(objectMapper.writeValueAsString(registerIn))
                .contentType(MediaType.APPLICATION_JSON));
    }

}
