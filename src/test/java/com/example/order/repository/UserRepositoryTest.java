package com.example.order.repository;

import com.example.order.domain.User;
import com.example.order.mock.UserMock;
import com.example.order.service.dto.IPageUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

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

  @Test
  @DisplayName("기본 조회 로직")
  void findAllProjectedBy() throws Exception {

    // given
    User entity = userRepository.save(UserMock.createUser());

    userRepository.flush();

    PageRequest pageRequest = UserMock.createdPageRequest();

    // when
    Page<IPageUser> users = userRepository.findAllProjectedBy(pageRequest, IPageUser.class);

    // then
    List<IPageUser> contents = users.getContent();

    Optional<IPageUser> contentOptional =
        contents.stream().filter(value -> value.getId() == entity.getId()).findFirst();

    Assertions.assertTrue(contentOptional.isPresent());

    IPageUser data = contentOptional.get();

    Assertions.assertEquals(data.getEmail(), entity.getEmail());
    Assertions.assertEquals(data.getName(), entity.getName());
    Assertions.assertEquals(data.getNickName(), entity.getNickName());
    Assertions.assertEquals(data.getPhoneNumber(), entity.getPhoneNumber());
    Assertions.assertEquals(data.getGender(), entity.getGender());
  }

  @Test
  @DisplayName("이메일 조회")
  void findAllByEmailContaining() throws Exception {

    // given
    User entity = userRepository.save(UserMock.createUser());

    userRepository.flush();

    PageRequest pageRequest = UserMock.createdPageRequest();

    // when
    Page<IPageUser> users =
        userRepository.findAllByEmailContaining(pageRequest, entity.getEmail(), IPageUser.class);

    // then
    List<IPageUser> contents = users.getContent();

    Optional<IPageUser> contentOptional =
        contents.stream().filter(value -> value.getId() == entity.getId()).findFirst();

    Assertions.assertTrue(contentOptional.isPresent());

    IPageUser data = contentOptional.get();

    Assertions.assertEquals(data.getEmail(), entity.getEmail());
    Assertions.assertEquals(data.getName(), entity.getName());
    Assertions.assertEquals(data.getNickName(), entity.getNickName());
    Assertions.assertEquals(data.getPhoneNumber(), entity.getPhoneNumber());
    Assertions.assertEquals(data.getGender(), entity.getGender());
  }

  @Test
  @DisplayName("이름 조회 로직")
  void findAllByNameContaining() throws Exception {

    // given
    User entity = userRepository.save(UserMock.createUser());

    userRepository.flush();

    PageRequest pageRequest = UserMock.createdPageRequest();

    // when
    Page<IPageUser> users =
        userRepository.findAllByNameContaining(pageRequest, entity.getName(), IPageUser.class);

    // then
    List<IPageUser> contents = users.getContent();

    Optional<IPageUser> contentOptional =
        contents.stream().filter(value -> value.getId() == entity.getId()).findFirst();

    Assertions.assertTrue(contentOptional.isPresent());

    IPageUser data = contentOptional.get();

    Assertions.assertEquals(data.getEmail(), entity.getEmail());
    Assertions.assertEquals(data.getName(), entity.getName());
    Assertions.assertEquals(data.getNickName(), entity.getNickName());
    Assertions.assertEquals(data.getPhoneNumber(), entity.getPhoneNumber());
    Assertions.assertEquals(data.getGender(), entity.getGender());
  }

  @Test
  @DisplayName("이름 or 이메일 조회 로직")
  void findAllByNameContainingOrEmailContaining() throws Exception {

    // given
    User entity = userRepository.save(UserMock.createUser());

    userRepository.flush();

    PageRequest pageRequest = UserMock.createdPageRequest();

    // when
    Page<IPageUser> users =
        userRepository.findAllByNameContainingOrEmailContaining(
            pageRequest, entity.getName(), entity.getEmail(), IPageUser.class);

    // then
    List<IPageUser> contents = users.getContent();

    Optional<IPageUser> contentOptional =
        contents.stream().filter(value -> value.getId() == entity.getId()).findFirst();

    Assertions.assertTrue(contentOptional.isPresent());

    IPageUser data = contentOptional.get();

    Assertions.assertEquals(data.getEmail(), entity.getEmail());
    Assertions.assertEquals(data.getName(), entity.getName());
    Assertions.assertEquals(data.getNickName(), entity.getNickName());
    Assertions.assertEquals(data.getPhoneNumber(), entity.getPhoneNumber());
    Assertions.assertEquals(data.getGender(), entity.getGender());
  }
}
