package com.example.order.repository;

import com.example.order.domain.User;
import com.example.order.enums.Gender;
import com.example.order.mock.UserMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@ActiveProfiles({"local"})
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

  @Autowired private UserRepository userRepository;

  @BeforeEach
  void init() {}

  @Test
  @DisplayName("유저 저장 로직")
  void saveUser() throws Exception {

    // given
    User entity = UserMock.createUser();

    // when
    User data = userRepository.save(entity);

    // then
    Assertions.assertNotNull(entity.getId());
    Assertions.assertEquals(data.getEmail(), entity.getEmail());
    Assertions.assertEquals(data.getPassword(), entity.getPassword());
    Assertions.assertEquals(data.getName(), entity.getName());
    Assertions.assertEquals(data.getNickName(), entity.getNickName());
    Assertions.assertEquals(data.getPhoneNumber(), entity.getPhoneNumber());
    Assertions.assertEquals(data.getGender(), entity.getGender());
  }

  @Test
  @DisplayName("유저 저장 로직 중복 저장시 DataIntegrityViolationException 치리")
  void saveUsers() throws Exception {

    // given
    List<User> users = UserMock.createUsers();

    User data1 = userRepository.save(users.get(0));
    userRepository.flush();

    User data2 = userRepository.save(users.get(1));

    Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.flush());
  }
}
