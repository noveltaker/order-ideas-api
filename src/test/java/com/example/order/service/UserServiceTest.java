package com.example.order.service;

import com.example.order.domain.User;
import com.example.order.enums.Gender;
import com.example.order.repository.UserRepository;
import com.example.order.service.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private Long DEFAULT_ID = 0L;

    private final String DEFAULT_EMAIL = "test@naver.com";

    private final String DEFAULT_PASSWORD = "1234567890";

    private final String DEFAULT_NAME = "가나다";

    private final String DEFAULT_NICKNAME = "testetttaa";

    private final String DEFAULT_PHONE_NUMBER = "010000000000";

    private final Gender DEFAULT_GENDER = Gender.M;

    private User entity;

    @BeforeEach
    void init() {

        entity = User.builder()
                .email(DEFAULT_EMAIL)
                .password(DEFAULT_PASSWORD)
                .name(DEFAULT_NAME)
                .nickName(DEFAULT_NICKNAME)
                .phoneNumber(DEFAULT_PHONE_NUMBER)
                .gender(DEFAULT_GENDER)
                .build();

        userRepository.save(entity);

        DEFAULT_ID = entity.getId();

    }

    @Test
    @DisplayName("유저 회원 가입 테스트 케이스")
    void joinUser() throws Exception {

        String email = "test@naver.com";

        String password = "12345";

        String name = "user";

        String nickName = "tester";

        String phoneNumber = "0100000000";

        Gender gender = Gender.M;

        UserDTO userDTO = new UserDTO(
                email,
                password,
                name,
                nickName,
                phoneNumber,
                gender
        );

        User entity = userService.joinUser(userDTO);

        // then
        User dbUser = userRepository.findById(entity.getId()).orElseThrow();

        Assertions.assertEquals(entity.getEmail(), dbUser.getEmail());

    }


    @Test
    @DisplayName("유저 회원 가입 테스트 케이스 - 성별이 null 일때")
    void joinUserGenderNull() throws Exception {

        String email = "test@naver.com";

        String password = "12345";

        String name = "user";

        String nickName = "tester";

        String phoneNumber = "0100000000";

        Gender gender = null;

        UserDTO userDTO = new UserDTO(
                email,
                password,
                name,
                nickName,
                phoneNumber,
                gender
        );

        User entity = userService.joinUser(userDTO);

        Assertions.assertNotNull(entity.getId());
    }

    @Test
    @DisplayName("유저 회원 가입 테스트 케이스 - phone number 에 - 들어갈때")
    void joinUserPhoneNum() throws Exception {

        String email = "test@naver.com";

        String password = "12345";

        String name = "user";

        String nickName = "tester";

        String phoneNumber = "01000-00000";

        Gender gender = null;

        UserDTO userDTO = new UserDTO(
                email,
                password,
                name,
                nickName,
                phoneNumber,
                gender
        );

        User entity = userService.joinUser(userDTO);

        Assertions.assertNotNull(entity.getId());
    }

    @Test
    @DisplayName("단일 회원 상세  정보 조회 기능")
    void getUser() throws Exception {
        User user = userService.getUser(DEFAULT_ID);
        Assertions.assertEquals(user, entity);
    }
}
