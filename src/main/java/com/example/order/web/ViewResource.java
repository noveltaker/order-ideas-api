package com.example.order.web;

import com.example.order.service.dto.LoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ViewResource {

  @PostMapping("/login")
  void login(@Valid @RequestBody LoginDTO dto) {}
}
