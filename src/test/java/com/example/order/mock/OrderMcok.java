package com.example.order.mock;

import com.example.order.domain.Order;
import com.example.order.domain.User;

public class OrderMcok {

  private static User user = UserMock.createUser();

  private static String DEFAULT_NAME = "test..";

  private static String EMORJI_NAME = "👺 이모지입니다.";

  public static Order createdOrder() {
    return Order.builder().user(user).name(DEFAULT_NAME).build();
  }

  public static Order createdOrderByEMorji() {
    return Order.builder().user(user).name(EMORJI_NAME).build();
  }
}
