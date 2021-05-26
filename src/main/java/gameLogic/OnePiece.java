package gameLogic;

import controller.Drawer;

import javafx.scene.shape.Circle;
import lombok.Data;

/**
 * Class that represents player 1's pieces on the board.
 */

public class OnePiece extends Piece  {

    /**
     *Represents an active red piece on the board.
     * @param board the board the game is played on
     */

    public OnePiece(Board board) {
        super(board);
    }

    @Override
    public void Visit(Drawer drawer, Circle circle) {
        drawer.draw(this, circle);
    }

    @Override
    public void take() {
        super.take();
        myBoard.reduceOnes();
    }

    @Override
    public boolean equals(Piece piece) {
        return piece.equals(this);
    }

    @Override
    public boolean equals(OnePiece piece) {
        return true;
    }

    @Override
    public boolean equals(TwoPiece piece) {
        return false;
    }

    @Override
    public boolean equals(NeutralPiece piece) {
        return false;
    }
}
