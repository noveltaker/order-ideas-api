package com.example.order.mock;

import com.example.order.config.security.SecurityConstants;
import com.example.order.domain.Authority;
import com.example.order.domain.User;
import com.example.order.enums.Gender;
import com.example.order.service.dto.UserDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserMock {

  private static String email = "test@naver.com";

  private static String password = "12345";

  private static String name = "user";

  private static String nickName = "tester";

  private static String phoneNumber = "0100000000";

  private static Gender gender = Gender.M;

  private static Long id = 1L;

  public static User createUser() {
    return User.builder()
        .id(id)
        .email(email)
        .password(password)
        .name(name)
        .nickName(nickName)
        .phoneNumber(phoneNumber)
        .gender(gender)
        .authorities(getAuthorities())
        .build();
  }
  public static User createUser(PasswordEncoder passwordEncoder) {
    return User.builder()
        .id(id)
        .email(email)
        .password(passwordEncoder.encode(password))
        .name(name)
        .nickName(nickName)
        .phoneNumber(phoneNumber)
        .gender(gender)
        .authorities(getAuthorities())
        .build();
  }

  public static List<User> createUsers() {

    User otherUser =
        User.builder()
            .id(2L)
            .email(email)
            .password(password)
            .name(name)
            .nickName(nickName)
            .phoneNumber(phoneNumber)
            .gender(gender)
            .build();

    return List.of(createUser(), otherUser);
  }

  public static PageRequest createdPageRequest() {
    return PageRequest.of(0, 10);
  }

  public static Set<Authority> getAuthorities() {
    Set<Authority> authorities = new HashSet<>();
    authorities.add(Authority.builder().name(SecurityConstants.ROLE_USER).build());
    return authorities;
  }

  public static UserDTO createUserDTO() {
    User entity = createUser();
    return new UserDTO(
        entity.getEmail(),
        entity.getPassword(),
        entity.getName(),
        entity.getNickName(),
        entity.getPhoneNumber(),
        entity.getGender());
  }
}
