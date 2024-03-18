package com.kata.tennis.services;

import com.kata.tennis.entities.Game;

public interface GameService {

  void validateGameInput(final String input);

  Game runGame(final String input);
}
