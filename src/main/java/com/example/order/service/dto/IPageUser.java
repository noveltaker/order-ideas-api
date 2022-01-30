package com.example.order.service.dto;

import com.example.order.enums.Gender;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface IPageUser {

  Long getId();

  String getEmail();

  String getPassword();

  String getName();

  String getNickName();

  String getPhoneNumber();

  Gender getGender();

  @Value("#{target.orderList.size() > 0 ? target.orderList.get(0) : null }")
  ITopOrder getOrder();

  interface ITopOrder {
    String getNumber();

    String getName();

    Date getOrderDate();
  }
}
