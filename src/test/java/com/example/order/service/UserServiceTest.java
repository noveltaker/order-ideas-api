package com.example.order.service;

import com.example.order.domain.User;
import com.example.order.enums.Gender;
import com.example.order.repository.UserRepository;
import com.example.order.service.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
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
}
