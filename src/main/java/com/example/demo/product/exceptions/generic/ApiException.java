package com.example.demo.product.exceptions.generic;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
  private final ErrorCode errorCode;

  protected ApiException(ErrorCode errorCode, Object... args) {
    super(errorCode.formatMessage(args));
    this.errorCode = errorCode;
  }

}
