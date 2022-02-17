package com.example.order.config.security;

import com.example.order.domain.User;
import com.example.order.utils.AuthUtils;
import com.example.order.utils.JwtUtils;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtValidFilter extends OncePerRequestFilter {

  private final String jwtKey;

  public JwtValidFilter(String jwtKey) {
    this.jwtKey = jwtKey;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

    Claims claims = JwtUtils.parseJwtToken(authorization, jwtKey);

    Long id = Long.valueOf((Integer) claims.get("id"));

    String email = (String) claims.get("email");

    String name = (String) claims.get("name");

    List<GrantedAuthority> authorities =
        AuthUtils.getGrantedAuthority((List<String>) claims.get("authorities"));

    User loginUser = User.builder().id(id).email(email).name(name).build();

    DomainUser domainUser = new DomainUser(loginUser, authorities);

    UsernamePasswordAuthenticationToken authorityUser =
        new UsernamePasswordAuthenticationToken(
            domainUser, domainUser.getPassword(), domainUser.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authorityUser);

    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String uri = request.getRequestURI();
    String method = request.getMethod();
    return !((uri.startsWith("/api/user") && !"POST".equals(method))
        || uri.startsWith("/api/users"));
  }
}
