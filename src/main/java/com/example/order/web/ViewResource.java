package com.example.order.web;

import com.example.order.service.TokenService;
import com.example.order.service.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ViewResource {

  private final TokenService tokenService;

  @PostMapping("/login")
  void login(@Valid @RequestBody LoginDTO dto) {}

  @PostMapping("/logout/{userId}")
  void logout(@PathVariable Long userId) {
    tokenService.removeToken(userId);
  }
}
