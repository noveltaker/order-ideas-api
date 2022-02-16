package com.example.order.repository;

import com.example.order.domain.Order;
import com.example.order.mock.OrderMock;
import com.example.order.service.dto.IOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles({"local"})
class OrderRepositoryTest {

  @Autowired private OrderRepository orderRepository;

  @Autowired private UserRepository userRepository;

  @Test
  @DisplayName("주문 저장 테스트")
  void save_success() throws Exception {

    // given
    Order order = OrderMock.createdOrder();

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
    Order order = OrderMock.createdOrderByEMorji();

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
  @DisplayName("유저별 주문 리스트 조회")
  public void find_user_id() throws Exception {

    // given
    Order order = orderRepository.save(OrderMock.createdOrder());

    // when
    List<IOrder> list = orderRepository.findByUser_IdOrderByOrderDateDesc(1L, IOrder.class);

    // init data 8 , given data 1
    assertEquals(9, list.size());

    Optional<IOrder> dataOptional =
        list.stream().filter(value -> order.getNumber().equals(value.getNumber())).findFirst();

    assertTrue(dataOptional.isPresent());

    IOrder data = dataOptional.get();
    assertEquals(order.getNumber(), data.getNumber());
    assertEquals(order.getName(), data.getName());
    assertEquals(order.getOrderDate(), data.getOrderDate());
  }
}
