package com.example.order.config;

import com.example.order.config.security.GlobalException;
import com.example.order.enums.ErrorType;
import com.example.order.service.dto.MessageDTO;
import com.example.order.utils.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalHandler {

  @ResponseBody
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity handler(Exception ex, HttpServletResponse response) {
    ErrorType errorType = ErrorType.INTERNAL_SERVER_ERROR;
    response.reset();
    MessageDTO.Error message = HttpUtils.setMessage(ex, errorType);
    return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ResponseBody
  @ExceptionHandler(GlobalException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity handler(GlobalException ex, HttpServletResponse response) {
    ErrorType errorType = ex.getErrorType();
    response.reset();
    MessageDTO.Error message = HttpUtils.setMessage(ex, errorType);
    return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
