package com.example.order.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

public class DomainUser extends User {

  private Long id;

  private String name;

  public DomainUser(com.example.order.domain.User user, List<GrantedAuthority> authorities) {
    super(user.getEmail(), user.getPassword(), authorities);
    this.id = user.getId();
    this.name = user.getName();
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return name;
  }

  public List<String> getAuthoritiesToStringList() {
    return this.getAuthorities().stream()
        .map(grantedAuthority -> grantedAuthority.getAuthority())
        .collect(Collectors.toList());
  }
}
