package com.example.order.domain;

import com.example.order.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Table
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(name = "password_hash", nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String nickName;

  @Column(nullable = false)
  private String phoneNumber;

  @Column
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @JsonIgnore
  @BatchSize(size = 100)
  @OneToMany(
      targetEntity = Order.class,
      fetch = FetchType.EAGER,
      cascade = {CascadeType.REMOVE})
  @JoinColumn(name = "user_id")
  @OrderBy(value = "orderDate desc ")
  private List<Order> orderList = new ArrayList<>();

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinTable(
      name = "authority_user",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "authrity_name", referencedColumnName = "name"))
  private Set<Authority> authorities = new HashSet();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User) || Hibernate.getClass(o) != Hibernate.getClass(this)) return false;
    User user = (User) o;
    return Objects.equals(user.id, this.id);
  }

  @Override
  public int hashCode() {
    return id.intValue();
  }

  @Builder
  public User(
      String email,
      String password,
      String name,
      String nickName,
      String phoneNumber,
      Gender gender) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.nickName = nickName;
    this.phoneNumber = phoneNumber;
    this.gender = gender;
  }

  public List<GrantedAuthority> getGrantedAuthority() {
    return this.authorities.stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getName()))
        .collect(Collectors.toList());
  }

  @Transient
  public void setNewUser(String encodePassword, Set<Authority> authorities) {
    this.password = encodePassword;
    this.authorities = authorities;
  }
}
