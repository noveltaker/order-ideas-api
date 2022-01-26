package com.example.order.web;

import com.example.order.enums.Gender;
import com.example.order.repository.UserRepository;
import com.example.order.service.UserService;
import com.example.order.service.dto.UserDTO;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserResource.class)
class UserResourceTest {

    private final String URI = "/api/user";

    private final String DEFAULT_EMAIL =  "test@naver.com";

    private final String DEFAULT_PASSWORD = "1234567890";

    private final String DEFAULT_NAME = "가나다";

    private final String DEFAULT_NICKNAME ="testetttaa";

    private final String DEFAULT_PHONE_NUMBER = "010000000000";

    private final Gender DEFAULT_GENDER = Gender.M;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserDTO userDTO;

    @BeforeEach
    void before() {

        userDTO = new UserDTO(
                DEFAULT_EMAIL,
                DEFAULT_PASSWORD,
                DEFAULT_NAME,
                DEFAULT_NICKNAME,
                DEFAULT_PHONE_NUMBER,
                DEFAULT_GENDER
        );

    }

    @Test
    @DisplayName("유저 저장")
    void joinUser_thenReturns200() throws Exception {

        Gson gson = new Gson();

        String content = gson.toJson(userDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(userService).joinUser(userDTO);
    }

}
