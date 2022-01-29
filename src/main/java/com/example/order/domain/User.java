package com.example.order.domain;

import com.example.order.enums.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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

  @Enumerated(EnumType.STRING)
  @Column
  private Gender gender;

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
}
