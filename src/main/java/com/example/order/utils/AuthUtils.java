package com.example.order.utils;

import com.example.order.domain.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthUtils {

  public static List<GrantedAuthority> getGrantedAuthority(Set<Authority> authorities) {
    if (authorities.isEmpty()) {
      throw new NullPointerException();
    }
    return authorities.stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getName()))
        .collect(Collectors.toList());
  }

  public static List<GrantedAuthority> getGrantedAuthority(List<String> authorities) {
    if (authorities.isEmpty()) {
      throw new NullPointerException();
    }
    return authorities.stream()
        .map(authority -> new SimpleGrantedAuthority(authority))
        .collect(Collectors.toList());
  }

  public static Set<Authority> test(List<String> authorities) {
    if (authorities.isEmpty()) {
      throw new NullPointerException();
    }
    return authorities.stream()
        .map(authName -> Authority.builder().name(authName).build())
        .collect(Collectors.toSet());
  }
}
