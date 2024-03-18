package com.kata.tennis.unit;

import com.kata.tennis.entities.GameStatus;
import com.kata.tennis.exceptions.ExceptionMessage;
import com.kata.tennis.exceptions.KataTennisException;
import com.kata.tennis.services.GameService;
import com.kata.tennis.services.GameServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GameServiceTest {


  @Test
  public void givenNullInputExpectEmptyInputError() {

    final GameService gameService = new GameServiceImpl();

    KataTennisException exception = Assertions.assertThrows(KataTennisException.class, () -> {
      gameService.validateGameInput(null);
    });

    Assertions.assertEquals(ExceptionMessage.EMPTY_INPUT.getMessage(), exception.getMessage());
    Assertions.assertEquals(ExceptionMessage.EMPTY_INPUT.getCode(), exception.getCode());

  }

  @Test
  public void givenEmptyInputExpectEmptyInputError() {

    final var input = "";
    final GameService gameService = new GameServiceImpl();

    KataTennisException exception = Assertions.assertThrows(KataTennisException.class, () -> {
      gameService.validateGameInput(input);
    });

    Assertions.assertEquals(ExceptionMessage.EMPTY_INPUT.getMessage(), exception.getMessage());
    Assertions.assertEquals(ExceptionMessage.EMPTY_INPUT.getCode(), exception.getCode());

  }

  @Test
  public void givenThreePlayersExpectInvalidPlaysNumberError() {

    final var input = "ABABZ";

    final GameService gameService = new GameServiceImpl();

    KataTennisException exception = Assertions.assertThrows(KataTennisException.class, () -> {
      gameService.validateGameInput(input);
    });

    Assertions.assertEquals(ExceptionMessage.INVALID_GAME_PLAYERS_NUMBER.getMessage(), exception.getMessage());
    Assertions.assertEquals(ExceptionMessage.INVALID_GAME_PLAYERS_NUMBER.getCode(), exception.getCode());
  }


  @Test
  public void givenOnePlayerInputExpectInvalidPlaysNumberError() {

    final var input = "A";

    final GameService gameService = new GameServiceImpl();

    KataTennisException exception = Assertions.assertThrows(KataTennisException.class, () -> {
      gameService.validateGameInput(input);
    });

    Assertions.assertEquals(ExceptionMessage.INVALID_GAME_PLAYERS_NUMBER.getMessage(), exception.getMessage());
    Assertions.assertEquals(ExceptionMessage.INVALID_GAME_PLAYERS_NUMBER.getCode(), exception.getCode());
  }

  @Test
  public void givenValidInputAndScoreExpectStatusGameFinishedAndPlayerAsWinner() {
    final var input = "ABABAA";

    final GameService gameService = new GameServiceImpl();

    final var gameResult = gameService.runGame(input);

    Assertions.assertNotNull(gameResult);
    Assertions.assertEquals(GameStatus.FINISHED, gameResult.getStatus());

    Assertions.assertNotNull(gameResult.getWinner());
    Assertions.assertEquals("A", gameResult.getWinner().name());
  }


  @Test
  public void givenValidInputAndScoreExpectStatusDeuce() {
    final var input = "ABABAB";

    final GameService gameService = new GameServiceImpl();

    final var gameResult = gameService.runGame(input);
    Assertions.assertNotNull(gameResult);
    Assertions.assertEquals(GameStatus.DEUCE, gameResult.getStatus());
  }

  @Test
  public void givenValidInputAndScoreExpectStatusDeuceAndPlayerBAdvantaged() {
    final var input = "ABABABB";

    final GameService gameService = new GameServiceImpl();

    final var gameResult = gameService.runGame(input);
    Assertions.assertNotNull(gameResult);
    Assertions.assertEquals(GameStatus.DEUCE, gameResult.getStatus());

    Assertions.assertNotNull(gameResult.getAdvantaged());
    Assertions.assertEquals("B", gameResult.getAdvantaged().name());
  }


  @Test
  public void givenValidInputAndScoreExpectStatusFinishedAndPlayerBWinner() {
    final var input = "ABABABBB";

    final GameService gameService = new GameServiceImpl();

    final var gameResult = gameService.runGame(input);
    Assertions.assertNotNull(gameResult);
    Assertions.assertEquals(GameStatus.FINISHED, gameResult.getStatus());

    Assertions.assertNotNull(gameResult.getAdvantaged());
    Assertions.assertEquals("B", gameResult.getAdvantaged().name());

    Assertions.assertNotNull(gameResult.getWinner());
    Assertions.assertEquals("B", gameResult.getWinner().name());
  }



  @Test
  public void givenValidInputAndScoreExpectStatusDeuceAndWithoutAdvantage() {
    final var input = "ABABABBA";

    final GameService gameService = new GameServiceImpl();

    final var gameResult = gameService.runGame(input);
    Assertions.assertNotNull(gameResult);
    Assertions.assertEquals(GameStatus.DEUCE, gameResult.getStatus());

    Assertions.assertNull(gameResult.getAdvantaged());
  }


  @Test
  public void givenValidInputAndScoreExpectStatusDeuceWithoutAdvantage() {
    final var input = "ABABAB";

    final GameService gameService = new GameServiceImpl();

    final var gameResult = gameService.runGame(input);
    Assertions.assertNotNull(gameResult);
    Assertions.assertEquals(GameStatus.DEUCE, gameResult.getStatus());

    Assertions.assertNull(gameResult.getAdvantaged());
  }
}
