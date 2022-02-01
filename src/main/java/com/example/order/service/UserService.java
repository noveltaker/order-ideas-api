package com.example.order.service;

import com.example.order.config.security.SecurityConstants;
import com.example.order.domain.Authority;
import com.example.order.domain.User;
import com.example.order.repository.UserRepository;
import com.example.order.service.dto.IPageUser;
import com.example.order.service.dto.IUser;
import com.example.order.service.dto.PageDTO;
import com.example.order.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Transactional(rollbackFor = Exception.class)
  public User joinUser(UserDTO dto) {
    User entity = dto.toEntity();
    entity.setNewUser(passwordEncoder.encode(dto.getPassword()), getAuthorities());
    userRepository.save(entity);
    return entity;
  }

  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public User getUser(Long id) {
    return userRepository.findById(id).orElse(User.builder().build());
  }

  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public Page<IPageUser> getUserList(PageDTO dto) {

    PageRequest pageRequest = PageRequest.of(dto.getPage(), dto.getSize());

    switch (dto.getSearchType()) {
      case EMAIL:
        return userRepository.findAllByEmailContaining(
            pageRequest, dto.getSearchValue(), IPageUser.class);
      case NAME:
        return userRepository.findAllByNameContaining(
            pageRequest, dto.getSearchValue(), IPageUser.class);
      case EMAIL_NAME:
        return userRepository.findAllByNameContainingOrEmailContaining(
            pageRequest, dto.getSearchValue(), dto.getSearchValue(), IPageUser.class);
      default:
        return userRepository.findAllProjectedBy(pageRequest, IPageUser.class);
    }
  }

  private Set<Authority> getAuthorities() {
    Set<Authority> authorities = new HashSet<>();
    authorities.add(Authority.builder().name(SecurityConstants.ROLE_USER).build());
    return authorities;
  }
}
