package com.example.order.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MessageDTO {

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class Login {
    private String accessToken;
    private String refreshToken;

    @Builder
    public Login(String accessToken, String refreshToken) {
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
    }
  }
}
