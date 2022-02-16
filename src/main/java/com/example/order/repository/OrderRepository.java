package com.example.order.repository;

import com.example.order.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

  @EntityGraph(attributePaths = {"user"})
  List<Order> findByUser_IdOrderByOrderDateDesc(Long userId);

  @EntityGraph(attributePaths = {"user"})
  <T> List<T> findByUser_IdOrderByOrderDateDesc(Long userId, Class<T> type);
}
