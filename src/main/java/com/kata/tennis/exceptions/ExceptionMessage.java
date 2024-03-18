package com.kata.tennis.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

  EMPTY_INPUT(1, "Game input is empty!"),
  INVALID_GAME_PLAYERS_NUMBER(2, "Number of players provided in the score does not respect tennis game rules ( 2 players )"),

  INVALID_GAME_STATUS(3, "Game ended in an invalid status.")
  ;

  private final int code;
  private final String message;


  ExceptionMessage(final int code, final String message) {
    this.code = code;
    this.message = message;
  }
}
