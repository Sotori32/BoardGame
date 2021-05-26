package controller;

import gameLogic.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Controller of the pieces.
 */

public class Drawer {

    private GridPane gridPane;

    /**
     * GridPane that represents the board.
     * @param gridPane the grids on the board
     */

    public Drawer(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    /**
     * Draw a piece according to the parameters.
     * @param piece the piece
     * @param circle the outline of the piece
     */

    public void draw(Piece piece, Circle circle) {
        piece.Visit(this, circle);
    }

    /**
     * Sets the coloration and outline of a red piece.
     * @param piece the piece displayed on the board
     * @param circle outline of the piece
     */

    public void draw(OnePiece piece, Circle circle) {
        circle.setFill(Color.RED);

        if(piece.isClickable()){
            circle.setStroke(Color.YELLOW);
            circle.setStrokeWidth(3.0);
        }
    }

    /**
     * Sets the coloration and outline of a blue piece.
     * @param piece the piece displayed on the board
     * @param circle outline of the piece
     */

    public void draw(TwoPiece piece, Circle circle) {
        circle.setFill(Color.BLUE);

        if(piece.isClickable()){
            circle.setStroke(Color.YELLOW);
            circle.setStrokeWidth(3.0);
        }
    }

    /**
     * Sets the coloration and outline of a neutral piece.
     * @param piece the piece displayed on the board
     * @param circle outline of the piece
     */

    public void draw(NeutralPiece piece, Circle circle) {
        circle.setFill(Color.GREEN);

        if(piece.isClickable()){
            circle.setStroke(Color.YELLOW);
            circle.setStrokeWidth(3.0);
        }
    }
}
