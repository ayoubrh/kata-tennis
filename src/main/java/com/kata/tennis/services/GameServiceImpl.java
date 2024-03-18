package com.kata.tennis.services;

import com.kata.tennis.entities.Game;
import com.kata.tennis.entities.GameStatus;
import com.kata.tennis.entities.Player;
import com.kata.tennis.exceptions.ExceptionMessage;
import com.kata.tennis.exceptions.KataTennisException;
import com.kata.tennis.utils.GamePrinter;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

public class GameServiceImpl implements GameService {

  @Override
  public void validateGameInput(final String input) {

    if (Objects.isNull(input) || input.isEmpty() || input.isBlank()) {
      throw new KataTennisException(ExceptionMessage.EMPTY_INPUT);
    }

    if(new LinkedHashSet<>(Arrays.asList(input.split(""))).size() != 2) {
      throw new KataTennisException(ExceptionMessage.INVALID_GAME_PLAYERS_NUMBER);
    }
  }

  @Override
  public Game runGame(final String input) {

    final var gameScore = Arrays.asList(input.split(""));

    final var listPlayers = new LinkedHashSet<>(gameScore).stream()
        .map(pl -> Player.builder().name(pl).build())
        .toList();

    final var game = Game.builder()
        .player1Score(new SimpleEntry<>(listPlayers.getFirst(), 0))
        .player2Score(new SimpleEntry<>(listPlayers.getLast(), 0))
        .build();

    final var scoreIterator = gameScore.iterator();

    while (scoreIterator.hasNext() && !GameStatus.FINISHED.equals(game.getStatus())) {

      if (GameStatus.STARTED.equals(game.getStatus())) {
        game.setStatus(GameStatus.ONGOING);
      }

      final var score = scoreIterator.next();
      if (score.equals(game.getPlayer1Score().getKey().name())) {
        updateScore(game, game.getPlayer1Score(), game.getPlayer2Score());
      } else {
        updateScore(game, game.getPlayer2Score(), game.getPlayer1Score());
      }
      GamePrinter.displayScore(game);
    }

    return game;
  }


  private void updateScore(final Game game, final SimpleEntry<Player, Integer> scored, final SimpleEntry<Player, Integer> concurrent) {
    switch (scored.getValue()) {
      case 0, 15 -> scored.setValue(scored.getValue() + 15);
      case 30 -> {
        if(concurrent.getValue() == 40) {
          game.setStatus(GameStatus.DEUCE);
        }
        scored.setValue(scored.getValue() + 10);
      }
      case 40 -> {
        if (GameStatus.DEUCE.equals(game.getStatus())) {
          if (Objects.nonNull(game.getAdvantaged()) && scored.getKey() == game.getAdvantaged()) {
            game.setStatus(GameStatus.FINISHED);
            game.setWinner(scored.getKey());
          } else if(Objects.nonNull(game.getAdvantaged()) && concurrent.getKey() == game.getAdvantaged()) {
            game.setAdvantaged(null);
          } else {
            game.setAdvantaged(scored.getKey());
          }
        } else {
          game.setStatus(GameStatus.FINISHED);
          game.setWinner(scored.getKey());
        }
      }
    }
  }
}
