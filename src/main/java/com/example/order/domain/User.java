package com.example.order.domain;

import com.example.order.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.*;

@Table
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
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

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "authority_user",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "authority_name", referencedColumnName = "name"))
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

  public String getPassword() {
    if (null == this.password || "" == this.password) return "";
    return password;
  }

  @Transient
  public void setNewUser(String encodePassword, Set<Authority> authorities) {
    this.password = encodePassword;
    this.authorities = authorities;
  }
}
