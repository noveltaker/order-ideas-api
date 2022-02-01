package com.example.order.config.security;

import com.example.order.domain.User;
import com.example.order.utils.JwtUtils;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtValidFilter extends OncePerRequestFilter {

  @Value("${jwt.key}")
  private String jwtKey;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

    Claims claims = JwtUtils.parseJwtToken(authorization, jwtKey);

    Long id = (Long) claims.get("id");

    String email = (String) claims.get("email");
    String name = (String) claims.get("name");

    List<String> authorities = (List<String>) claims.get("authorities");

    User loginUser = User.builder().build().jwtLogin(id, email, name, authorities);

    DomainUser domainUser = new DomainUser(loginUser);

    UsernamePasswordAuthenticationToken authorityUser =
        new UsernamePasswordAuthenticationToken(
            domainUser, domainUser.getPassword(), domainUser.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authorityUser);

    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String uri = request.getRequestURI();
    boolean test = !(uri.startsWith("/api/user") || uri.startsWith("/api/users"));
    return test;
  }
}
