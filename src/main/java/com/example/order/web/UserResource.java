package com.example.order.web;

import com.example.order.domain.User;
import com.example.order.service.UserService;
import com.example.order.service.dto.IUser;
import com.example.order.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final UserService service;

    @PostMapping("/user")
    public User joinUser(@RequestBody @Valid UserDTO userDTO) {
        return service.joinUser(userDTO);
    }

    @GetMapping("/user/{id}")
    public IUser showUser(@PathVariable Long id) {
        return service.getUser(id);
    }
}
