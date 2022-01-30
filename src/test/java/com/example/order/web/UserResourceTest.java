package com.example.order.web;

import com.example.order.enums.Gender;
import com.example.order.repository.UserRepository;
import com.example.order.service.OrderService;
import com.example.order.service.UserService;
import com.example.order.service.dto.UserDTO;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserResource.class)
class UserResourceTest {

  private final String URI = "/api/user";

  @Autowired private MockMvc mockMvc;

  @MockBean private UserService userService;

  @MockBean private OrderService orderService;

  @MockBean private UserRepository userRepository;

  @Nested
  @Disabled("저장 로직 테스트 케이스 컨트롤러 레이어")
  class Save {

    @Test
    @DisplayName("유저 저장")
    void joinUser_thenReturns200() throws Exception {

      UserDTO userDTO = createByUserDTO();

      Gson gson = new Gson();

      String content = gson.toJson(userDTO);

      mockMvc
          .perform(
              MockMvcRequestBuilders.post(URI)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(content))
          .andExpect(status().isOk())
          .andDo(print());

      Mockito.verify(userService).joinUser(userDTO);
    }
  }

  @Nested
  class Select {

    private UserDTO userDTO;

    @BeforeEach
    void before() {

      userDTO = createByUserDTO();
    }

    @Test
    @DisplayName("단일 회원 상세 정보 조회 기능")
    void showUser_thenReturn200() throws Exception {

      //        IUser IUser = null;
      //
      //        given(userService.getUser(any())).willReturn(IUser);
      //
      //        String api = URI + "/" + 1;
      //
      //        ResultActions result =
      // mockMvc.perform(get(api).contentType(MediaType.APPLICATION_JSON)).andDo(print());
      //
      //        result.andExpect(status().isOk())
      //                .andExpect(jsonPath("$.email", is(DEFAULT_EMAIL)))
      //                .andExpect(jsonPath("$.password", is(DEFAULT_PASSWORD)))
      //                .andExpect(jsonPath("$.name", is(DEFAULT_NAME)))
      //                .andExpect(jsonPath("$.nickName", is(DEFAULT_NICKNAME)))
      //                .andExpect(jsonPath("$.phoneNumber", is(DEFAULT_PHONE_NUMBER)))
      //                .andExpect(jsonPath("$.gender", is(DEFAULT_GENDER.name())))
      //                .andDo(print());
    }
  }

  private static UserDTO createByUserDTO() {
    final String DEFAULT_EMAIL = "test@naver.com";

    final String DEFAULT_PASSWORD = "1234567890";

    final String DEFAULT_NAME = "가나다";

    final String DEFAULT_NICKNAME = "testetttaa";

    final String DEFAULT_PHONE_NUMBER = "010000000000";

    final Gender DEFAULT_GENDER = Gender.M;

    return new UserDTO(
        DEFAULT_EMAIL,
        DEFAULT_PASSWORD,
        DEFAULT_NAME,
        DEFAULT_NICKNAME,
        DEFAULT_PHONE_NUMBER,
        DEFAULT_GENDER);
  }
}
