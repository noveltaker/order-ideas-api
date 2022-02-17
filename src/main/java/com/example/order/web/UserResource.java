package com.example.order.web;

import com.example.order.domain.Order;
import com.example.order.domain.User;
import com.example.order.service.OrderService;
import com.example.order.service.UserService;
import com.example.order.service.dto.*;
import com.example.order.web.docs.UserResourceDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource implements UserResourceDocs {

  private final UserService userService;

  private final OrderService orderService;

  @PostMapping("/user")
  public User joinUser(@RequestBody @Valid UserDTO userDTO) {
    return userService.joinUser(userDTO);
  }

  @GetMapping("/user/{id}")
  public IUser showUser(@PathVariable Long id) {
    return userService.getUser(id).orElseThrow(() -> new NullPointerException());
  }

  @GetMapping("/user/{userId}/orders")
  public List<IOrder> showOrderListByUser(@PathVariable Long userId) {
    return orderService.getOrderListByUser(userId);
  }

  @GetMapping("/users")
  public Page<IPageUser> showUsers(PageDTO dto) {
    return userService.getUserList(dto);
  }
}
