package gameLogic;

import lombok.Data;
import java.util.ArrayList;

/**
 * Class that represents a player.
 */
@Data
public class Player {

    /**
     * Pieces, that were taken by the player.
     */
    ArrayList<Piece> taken;

    /**
     * Name of the player.
     */
    String name;

    /**
     * Adds a piece to {@link #taken}.
     * @param piece piece taken by the player
     */
    public void addPiece(Piece piece){
        taken.add(piece);
    }

    /**
     * Returns the number of pieces the player has with the given color.
     * @param paint the color of the piece
     * @return the number of pieces with the given color
     */
    public int getNumberOfColor(String paint){
        int counter = 0;
        for(Piece piece: taken){
            if(piece.getPaint().equals(javafx.scene.paint.Paint.valueOf(paint)))
                counter++;
        }
        return counter;
    }

    /**
     * Constructor that creates a player with the given name.
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        taken = new ArrayList<Piece>();
    }


}
