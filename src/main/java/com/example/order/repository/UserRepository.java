package com.example.order.repository;

import com.example.order.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  <T> Optional<T> findById(Long aLong, Class<T> type);

  <T> Page<T> findAllProjectedBy(Pageable pageable, Class<T> type);

  <T> Page<T> findAllByEmailContaining(Pageable pageable, String email, Class<T> type);

  <T> Page<T> findAllByNameContaining(Pageable pageable, String name, Class<T> type);

  <T> Page<T> findAllByNameContainingOrEmailContaining(
      Pageable pageable, String name, String email, Class<T> type);
}
