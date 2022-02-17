package com.example.order.service;

import com.example.order.domain.Order;
import com.example.order.mock.OrderMock;
import com.example.order.repository.OrderRepository;
import com.example.order.service.dto.IOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderServiceTest {

  private OrderService orderService;

  @Mock private OrderRepository orderRepository;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    orderService = new OrderService(orderRepository);
  }

  @Test
  @DisplayName("유저 리스트 조회")
  void getOrderListByUser() {

    List<IOrder> givenData = OrderMock.createIOrderList();

    BDDMockito.given(
            orderRepository.findByUser_IdOrderByOrderDateDesc(any(Long.class), eq(IOrder.class)))
        .willReturn(givenData);

    List<IOrder> data = orderService.getOrderListByUser(1L);

    BDDMockito.then(orderRepository)
        .should()
        .findByUser_IdOrderByOrderDateDesc(any(Long.class), eq(IOrder.class));

    Assertions.assertEquals(givenData, data);
  }
}
