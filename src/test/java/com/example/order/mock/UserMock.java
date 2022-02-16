package com.example.order.mock;

import com.example.order.domain.User;
import com.example.order.enums.Gender;

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
        .build();
  }
}
