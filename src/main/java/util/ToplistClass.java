package util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class that is used in the toplist.
 */
@AllArgsConstructor
@Data
public class ToplistClass {
    /**
     * Name of the player.
     */
    private String player;

    /**
     * Number of games the player has won.
     */
    private int wins;

    /**
     * Increments the number of wins the player has by one.
     */
    public void addWin(){
        wins++;
    }

}
