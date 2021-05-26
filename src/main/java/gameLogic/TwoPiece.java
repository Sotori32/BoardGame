package gameLogic;

import controller.Drawer;

import javafx.scene.shape.Circle;
import lombok.Data;

/**
 * Class that represents player 2's pieces on the board.
 */
public class TwoPiece extends Piece {

    /**
     *Represents an active blue piece on the board.
     * @param board the board the game is played on
     */

    public TwoPiece(Board board) {
        super(board);
    }

    @Override
    public void Visit(Drawer drawer, Circle circle) {
        drawer.draw(this, circle);
    }

    @Override
    public void take() {
        super.take();
        myBoard.reduceTwos();
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
        return true;
    }

    @Override
    public boolean equals(NeutralPiece piece) {
        return false;
    }
}
