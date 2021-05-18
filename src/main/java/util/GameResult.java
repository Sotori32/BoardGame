package util;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

/**
 * Class that represents the result of a game.
 */
@Builder
@Data
public class GameResult {
    /**
     * The name of player one.
     */
    private String player1;

    /**
     * The name of player two.
     */
    private String player2;

    /**
     * The name of the winner.
     */
    private String  winner;

    /**
     * Date of when the game was finished.
     */
    private LocalDate date;

    /**
     * Number of turns it took to finish the game.
     */
    private int turns;

}
