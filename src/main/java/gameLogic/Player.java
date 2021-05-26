package gameLogic;

import lombok.Data;
import java.util.ArrayList;

/**
 * Class that represents a player.
 */
@Data
public class Player {

    /**
     * Pieces that were taken by the player.
     */
    private ArrayList<Piece> taken;

    /**
     * Name of the player.
     */
    private String name;

    /**
     * The piece assigned to the player.
     */
    private Piece ownPiece;

    /**
     * Adds a piece to {@link #taken}.
     * @param piece piece taken by the player
     */
    public void addPiece(Piece piece){
        taken.add(piece);
    }

    /**
     * The list of pieces the player has taken.
     * @param piece the pieces corresponding to the player
     */
    public Player(Piece piece) {
        taken = new ArrayList<Piece>();
        ownPiece = piece;
    }

    /**
     * Sets the name of the player.
     * @param name name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Tracks the score of the player.
     * @return the current score of the player
     */
    public int getScore() {
        int score = 0;

        for (Piece piece : taken) {
            if (piece.equals(ownPiece))
                ++score;
        }

        return score;
    }
}
