package com.example.order.mock;

import com.example.order.domain.Order;
import com.example.order.domain.User;
import com.example.order.service.dto.IOrder;
import com.example.order.utils.RandomUtils;

import java.util.Date;
import java.util.List;

public class OrderMock {

  private static User user = UserMock.createUser();

  private static String DEFAULT_NAME = "test..";

  private static String EMORJI_NAME = "👺 이모지입니다.";

  public static Order createdOrder() {
    return Order.builder().user(user).name(DEFAULT_NAME).build();
  }

  public static Order createdOrderByEMorji() {
    return Order.builder().user(user).name(EMORJI_NAME).build();
  }

  public static List<Order> createOrderList() {
    return List.of(createdOrder());
  }

  public static List<IOrder> createIOrderList() {
    return List.of(
        new IOrder() {
          @Override
          public String getNumber() {
            return RandomUtils.getRandomValue();
          }

          @Override
          public String getName() {
            return "주문1";
          }

          @Override
          public Date getOrderDate() {
            return new Date();
          }
        });
  }
}
