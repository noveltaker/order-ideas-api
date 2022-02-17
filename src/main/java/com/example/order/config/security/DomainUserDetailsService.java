package com.example.order.config.security;

import com.example.order.domain.User;
import com.example.order.repository.UserRepository;
import com.example.order.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userDetailsService")
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByEmail(username.toLowerCase())
            .orElseThrow(() -> new UsernameNotFoundException("login user " + username));

    List<GrantedAuthority> authorities = AuthUtils.getGrantedAuthority(user.getAuthorities());

    return new DomainUser(user, authorities);
  }
}
