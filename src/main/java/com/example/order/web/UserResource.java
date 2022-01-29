package com.example.order.web;

import com.example.order.domain.Order;
import com.example.order.domain.User;
import com.example.order.service.OrderService;
import com.example.order.service.UserService;
import com.example.order.service.dto.IUser;
import com.example.order.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

  private final UserService userService;

  private final OrderService orderService;

  @PostMapping("/user")
  public User joinUser(@RequestBody @Valid UserDTO userDTO) {
    return userService.joinUser(userDTO);
  }

  @GetMapping("/user/{id}")
  public IUser showUser(@PathVariable Long id) {
    return userService.getUser(id);
  }

  @GetMapping("/user/{userId}/orders")
  public List<Order> showOrderListByUser(@PathVariable Long userId) {
    return orderService.getOrderListByUser(userId);
  }
}
