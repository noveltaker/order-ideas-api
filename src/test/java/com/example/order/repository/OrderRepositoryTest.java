package com.example.order.repository;

import com.example.order.domain.Order;
import com.example.order.mock.OrderMcok;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles({"local"})
class OrderRepositoryTest {

  @Autowired private OrderRepository orderRepository;

  @Test
  @DisplayName("주문 저장 테스트")
  void save_success() throws Exception {

    // given
    Order order = OrderMcok.createdOrder();

    // when
    Order saveOrder = orderRepository.save(order);

    org.assertj.core.api.Assertions.assertThat(order).isEqualTo(saveOrder);

    // order number null checked
    assertNotNull(saveOrder.getNumber());

    // name checked
    assertEquals(order.getName(), saveOrder.getName());

    // order number checked
    assertEquals(order.getNumber(), saveOrder.getNumber());

    // order date checked
    assertEquals(order.getOrderDate(), saveOrder.getOrderDate());
  }

  @Test
  @DisplayName("이모자 저장 테스트")
  void save_이모지테스트() throws Exception {

    // given
    Order order = OrderMcok.createdOrderByEMorji();

    // when
    Order saveOrder = orderRepository.save(order);

    org.assertj.core.api.Assertions.assertThat(order).isEqualTo(saveOrder);

    // order number null checked
    assertNotNull(saveOrder.getNumber());

    // name checked
    assertEquals(order.getName(), saveOrder.getName());

    // order number checked
    assertEquals(order.getNumber(), saveOrder.getNumber());

    // order date checked
    assertEquals(order.getOrderDate(), saveOrder.getOrderDate());
  }
}
