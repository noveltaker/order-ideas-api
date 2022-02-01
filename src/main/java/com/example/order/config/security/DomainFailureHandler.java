package com.example.order.config.security;

import com.example.order.enums.ErrorType;
import com.example.order.enums.ExceptionType;
import com.example.order.service.dto.MessageDTO;
import com.example.order.utils.HttpUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationFailureHandler")
public class DomainFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {

    String exceptionMessage = getExceptionMessage(exception);

    MessageDTO.Error message =
        MessageDTO.Error.builder()
            .code(ErrorType.AUTHENTICATION_ERROR.getCode())
            .message(exceptionMessage)
            .detailMessage(ExceptionUtils.getStackTrace(exception))
            .build();

    HttpUtils.createMessage(HttpStatus.UNAUTHORIZED, response, message);
  }

  private String getExceptionMessage(AuthenticationException exception) {
    ExceptionType authenticationTypes = ExceptionType.findOf(exception.getClass().getSimpleName());
    return authenticationTypes.getValue();
  }
}
