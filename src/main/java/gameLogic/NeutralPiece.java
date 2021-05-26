package gameLogic;

import controller.Drawer;

import javafx.scene.shape.Circle;
import lombok.Data;

/**
 * Class that represents neutral pieces on the board.
 */
public class NeutralPiece extends Piece {

    /**
     *Represents an active neutral piece on the board.
     * @param board the board the game is played on
     */

    public NeutralPiece(Board board) {
        super(board);
        clickable = true;
    }

    @Override
    public void Visit(Drawer drawer, Circle circle) {
        drawer.draw(this, circle);
    }

    @Override
    public boolean equals(Piece piece) {
        return piece.equals(this);
    }

    @Override
    public boolean equals(OnePiece piece) {
        return false;
    }

    @Override
    public boolean equals(TwoPiece piece) {
        return false;
    }

    @Override
    public boolean equals(NeutralPiece piece) {
        return true;
    }
}
