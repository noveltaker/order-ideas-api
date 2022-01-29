package com.example.order.repository;

import com.example.order.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  <T> Optional<T> findById(Long aLong, Class<T> type);

  Page<User> findAllByEmailContainingOrNameContaining(Pageable pageable, String email, String name);
}
