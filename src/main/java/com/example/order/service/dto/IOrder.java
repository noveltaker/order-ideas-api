package com.example.order.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface IOrder {

  @Value("#{target.number}")
  String getNumber();

  @Value("#{target.name}")
  String getName();

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "Asia/Seoul")
  @Value("#{target.orderDate}")
  Date getOrderDate();
}
