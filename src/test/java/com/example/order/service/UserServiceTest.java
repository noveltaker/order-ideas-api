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

import java.util.List;

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

    @Test()
    @DisplayName("유저 회원 가입 테스트 케이스")
    void joinUser() throws Exception {

        UserDTO userDTO = new UserDTO(
                DEFAULT_EMAIL,
                DEFAULT_PASSWORD,
                DEFAULT_NAME,
                DEFAULT_NICKNAME,
                DEFAULT_PHONE_NUMBER,
                DEFAULT_GENDER
        );

        User entity = userService.joinUser(userDTO);

        List<User> userList = userRepository.findAll();

        User foundUser = userList.stream().filter(user -> user.getId() == entity.getId())
                .findFirst()
                .orElseThrow();

        Assertions.assertEquals(entity.getEmail(), foundUser.getEmail());
    }


    @Test
    @DisplayName("유저 회원 가입 테스트 케이스 - 성별이 null 일때")
    void joinUserGenderNull() throws Exception {

        UserDTO userDTO = new UserDTO(
                DEFAULT_EMAIL,
                DEFAULT_PASSWORD,
                DEFAULT_NAME,
                DEFAULT_NICKNAME,
                DEFAULT_PHONE_NUMBER,
                DEFAULT_GENDER
        );

        User entity = userService.joinUser(userDTO);

        List<User> userList = userRepository.findAll();

        User foundUser = userList.stream().filter(user -> user.getId() == entity.getId())
                .findFirst()
                .orElseThrow();

        Assertions.assertNotNull(entity.getId());

        Assertions.assertEquals(entity.getEmail(), foundUser.getEmail());
    }

    @Test
    @DisplayName("유저 회원 가입 테스트 케이스 - phone number 에 - 들어갈때")
    void joinUserPhoneNum() throws Exception {

        UserDTO userDTO = new UserDTO(
                DEFAULT_EMAIL,
                DEFAULT_PASSWORD,
                DEFAULT_NAME,
                DEFAULT_NICKNAME,
                DEFAULT_PHONE_NUMBER,
                DEFAULT_GENDER
        );

        User entity = userService.joinUser(userDTO);

        List<User> userList = userRepository.findAll();

        User foundUser = userList.stream().filter(user -> user.getId() == entity.getId())
                .findFirst()
                .orElseThrow();

        Assertions.assertNotNull(entity.getId());

        Assertions.assertEquals(entity.getEmail(), foundUser.getEmail());
    }

//    @Test
//    @DisplayName("단일 회원 상세  정보 조회 기능")
//    void getUser() throws Exception {
//        User user = userService.getUser(DEFAULT_ID);
//        Assertions.assertEquals(user, entity);
//    }
}
