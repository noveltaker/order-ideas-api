package com.example.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

  @Id
  @Column(unique = true, nullable = false)
  private Long userId;

  @Column(nullable = false)
  private String refreshToken;

  @Builder
  public RefreshToken(Long userId, String refreshToken) {
    this.userId = userId;
    this.refreshToken = refreshToken;
  }
}
