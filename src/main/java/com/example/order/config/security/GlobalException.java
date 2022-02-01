package com.example.order.config.security;

import com.example.order.enums.ErrorType;

public class GlobalException extends RuntimeException {

  private ErrorType errorType;

  public GlobalException(ErrorType errorType) {
    this.errorType = errorType;
  }

  public ErrorType getErrorType() {
    return errorType;
  }
}
