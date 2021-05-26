package gameLogic;

import controller.Drawer;
import javafx.scene.shape.Circle;
import lombok.Data;

/**
 * Class that represents a piece on the board.
 */
@Data
public abstract class Piece {

    /**
     * Indicates whether the piece was taken by a player.
     */
    protected boolean isTaken;

    /**
     * Indicates whether the piece is clickable.
     */
    protected boolean clickable;

    /**
     * Indicates the player.
     */
    protected Player myPlayer;

    /**
     * Indicates the board.
     */
    protected Board myBoard;

    /**
     * Represents a piece on the board.
     * @param board the board
     */
    public Piece(Board board) {
        isTaken = false;
        clickable = false;
        myBoard = board;
    }

    /**
     * Visitor for the Drawer class that determines the coloration of the pieces.
     * @param drawer the parameters of a certain piece set by the Drawer class
     * @param circle the parameters of the outline of a certain piece set by the Drawer class
     */
    public abstract void Visit(Drawer drawer, Circle circle);

    /**
     *Takes a piece from the board.
     */
    public void take() {
        isTaken = true;
    }

    /**
     * Returns if the piece has been taken.
     * @return returns the state of the piece having been taken or not
     */
    public boolean isTaken() {
        return isTaken;
    }

    /**
     * Returns if the piece is clickable.
     * @return returns the state of the piece if it is active or not
     */
    public boolean isClickable() {
        return clickable;
    }

    /**
     * Checks if it is a piece.
     * @param piece the piece in question
     * @return returns if it is or not
     */
    public abstract boolean equals(Piece piece);

    /**
     * Checks if it is a red piece.
     * @param piece the piece in question
     * @return returns if it is or not
     */
    public abstract boolean equals(OnePiece piece);

    /**
     * Checks if it is a blue piece.
     * @param piece the piece in question
     * @return returns if it is or not
     */
    public abstract boolean equals(TwoPiece piece);

    /**
     * Checks if it is a neutral piece.
     * @param piece the piece in question
     * @return returns if it is or not
     */
    public abstract boolean equals(NeutralPiece piece);
}
