package com.example.order.service;

import com.example.order.repository.OrderRepository;
import com.example.order.service.dto.IOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  @Transactional(rollbackFor = Exception.class, readOnly = true)
  public List<IOrder> getOrderListByUser(Long userId) {
    return orderRepository.findByUser_IdOrderByOrderDateDesc(userId, IOrder.class);
  }
}
