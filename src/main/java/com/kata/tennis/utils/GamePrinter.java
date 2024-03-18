package com.kata.tennis.utils;

import com.kata.tennis.entities.Game;
import com.kata.tennis.entities.GameStatus;
import com.kata.tennis.exceptions.ExceptionMessage;
import com.kata.tennis.exceptions.KataTennisException;

public final class GamePrinter {

  public static void displayScore(final Game game) {
    if (!GameStatus.FINISHED.equals(game.getStatus())) {
      System.out.printf("Player %s : %s / Player %s : %s %n",
          game.getPlayer1Score().getKey().name(), game.getPlayer1Score().getValue().toString(),
          game.getPlayer2Score().getKey().name(), game.getPlayer2Score().getValue().toString());
    }

  }

  public static void displayResult(final Game game) {

    switch (game.getStatus()) {
      case FINISHED -> System.out.printf("Player %s wins the game.%n", game.getWinner().name());
      case ONGOING -> System.out.println("The Game didn't finish with the provided score.");
      case DEUCE -> System.out.println("The provided score ended the Game in 'deuce' situation.");
      case STARTED -> System.out.println("The game has just started.");
      default -> throw new KataTennisException(ExceptionMessage.INVALID_GAME_STATUS);
    }
  }

}
