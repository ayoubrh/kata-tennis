package com.kata.tennis.entities;

import java.util.AbstractMap.SimpleEntry;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Game {

    private SimpleEntry<Player, Integer> player1Score;
    private SimpleEntry<Player, Integer> player2Score;

    @Builder.Default
    private GameStatus status = GameStatus.STARTED;
    private Player advantaged;
    private Player winner;

}
