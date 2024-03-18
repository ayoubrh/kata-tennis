package com.kata.tennis.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KataTennisException extends RuntimeException {

  private final int code;
  private final String message;
  public KataTennisException(final ExceptionMessage exceptionMessage) {
    this.code = exceptionMessage.getCode();
    this.message = exceptionMessage.getMessage();
  }

  public KataTennisException(final String message) {
    this.message = message;
    this.code = 999;
  }

}
