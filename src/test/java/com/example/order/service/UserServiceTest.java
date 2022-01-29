package com.example.order.service;

import com.example.order.domain.User;
import com.example.order.enums.Gender;
import com.example.order.repository.UserRepository;
import com.example.order.service.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private Long DEFAULT_ID = 0L;

    private final String DEFAULT_EMAIL = "test@naver.com";

    private final String DEFAULT_PASSWORD = "1234567890";

    private final String DEFAULT_NAME = "가나다";

    private final String DEFAULT_NICKNAME = "testetttaa";

    private final String DEFAULT_PHONE_NUMBER = "010000000000";

    private final Gender DEFAULT_GENDER = Gender.M;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test()
    @Transactional
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

        User user = userDTO.toEntity();

        Mockito.lenient().when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.joinUser(userDTO);

        Assertions.assertEquals(result.getEmail(), user.getEmail());

    }

    @Test
    @DisplayName("단일 회원 상세 정보 조회 기능")
    void getUser() throws Exception {

        UserDTO userDTO = new UserDTO(
                DEFAULT_EMAIL,
                DEFAULT_PASSWORD,
                DEFAULT_NAME,
                DEFAULT_NICKNAME,
                DEFAULT_PHONE_NUMBER,
                DEFAULT_GENDER
        );

        Mockito.lenient().when(userRepository.findById(1L))
                .thenReturn(Optional.ofNullable(userDTO.toEntity()));

//        User user = userService.getUser(1L);
    }
}
