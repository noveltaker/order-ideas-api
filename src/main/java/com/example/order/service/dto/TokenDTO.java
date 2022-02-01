package com.example.order.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class TokenDTO {

  @NotNull private Long userId;

  @NotNull private String refreshToken;
}
