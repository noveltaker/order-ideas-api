package com.example.order.service;

import com.example.order.domain.User;
import com.example.order.repository.UserRepository;
import com.example.order.service.dto.IUser;
import com.example.order.service.dto.PageDTO;
import com.example.order.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional(rollbackFor = Exception.class)
  public User joinUser(UserDTO dto) {
    User entity = dto.toEntity();
    userRepository.save(entity);
    return entity;
  }

  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public IUser getUser(Long id) {
    return userRepository.findById(id, IUser.class).orElseThrow();
  }

  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public Page<User> getUserList(PageDTO dto) {
    PageRequest pageRequest = PageRequest.of(dto.getPage(), dto.getSize());
    if (dto.isNotEmptySearch()) {
      return userRepository.findAllByEmailContainingOrNameContaining(
          pageRequest, dto.getSearchString(), dto.getSearchString());
    }
    return userRepository.findAll(pageRequest);
  }
}
