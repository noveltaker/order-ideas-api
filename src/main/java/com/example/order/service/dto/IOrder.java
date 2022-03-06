package com.example.order.service.dto;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface IOrder {

  @Value("#{target.number}")
  String getNumber();

  @Value("#{target.name}")
  String getName();

  @Value("#{target.orderDate}")
  Date getOrderDate();
}
