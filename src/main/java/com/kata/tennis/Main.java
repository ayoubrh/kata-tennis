package com.kata.tennis;

import com.kata.tennis.exceptions.KataTennisException;
import com.kata.tennis.services.GameService;
import com.kata.tennis.services.GameServiceImpl;
import com.kata.tennis.utils.GamePrinter;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    System.out.print("You can set the game score here : ");

    final var userInput = new Scanner(System.in);
    final var input = userInput.nextLine();
    userInput.close();

    final GameService gameService = new GameServiceImpl();
    try {

      gameService.validateGameInput(input);
      final var gameResult = gameService.runGame(input);
      GamePrinter.displayResult(gameResult);

    } catch (KataTennisException e) {
      System.out.println(e.getMessage());
      System.exit(e.getCode());
    } catch (Exception e) {

      System.out.println("Unexpected error !");
      System.exit(999);
    }
  }
}