package com.example.order.service;

import com.example.order.domain.User;
import com.example.order.mock.UserMock;
import com.example.order.repository.UserRepository;
import com.example.order.service.dto.IUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

  private UserService userService;

  @Mock private UserRepository userRepository;

  @Mock private PasswordEncoder passwordEncoder;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    userService = new UserService(userRepository, passwordEncoder);
  }

  @Test
  @Transactional
  @DisplayName("회원 가입 로직")
  void joinUser() throws Exception {

    User entity = UserMock.createUser(passwordEncoder);

    BDDMockito.given(userRepository.save(any())).willReturn(entity);

    User data = userService.joinUser(UserMock.createUserDTO());

    BDDMockito.then(userRepository).should().save(any());

    org.junit.jupiter.api.Assertions.assertEquals(data.getEmail(), entity.getEmail());
    org.junit.jupiter.api.Assertions.assertEquals(data.getName(), entity.getName());
    org.junit.jupiter.api.Assertions.assertEquals(data.getNickName(), entity.getNickName());
    org.junit.jupiter.api.Assertions.assertEquals(data.getPhoneNumber(), entity.getPhoneNumber());
    Assertions.assertEquals(data.getGender(), entity.getGender());
  }

  @Test
  @Transactional
  @DisplayName("단일 회원 조회")
  void getUser() throws Exception {

    Optional<IUser> entityOptional = UserMock.createIUser();

    BDDMockito.given(userRepository.findById(any(), eq(IUser.class))).willReturn(entityOptional);

    Optional<IUser> dataOptional = userService.getUser(1L);

    BDDMockito.then(userRepository).should().findById(any(), eq(IUser.class));

    org.assertj.core.api.Assertions.assertThat(entityOptional).isEqualTo(dataOptional);

    Assertions.assertTrue(dataOptional.isPresent());

    IUser data = dataOptional.get();

    IUser entity = entityOptional.get();

    org.junit.jupiter.api.Assertions.assertEquals(data.getEmail(), entity.getEmail());
    org.junit.jupiter.api.Assertions.assertEquals(data.getName(), entity.getName());
    org.junit.jupiter.api.Assertions.assertEquals(data.getNickName(), entity.getNickName());
    org.junit.jupiter.api.Assertions.assertEquals(data.getPhoneNumber(), entity.getPhoneNumber());
  }
}
