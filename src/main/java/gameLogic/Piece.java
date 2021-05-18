package gameLogic;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class that represents a piece on the board.
 */
@Data
@AllArgsConstructor
public class Piece {

    /**
     * The color of the piece.
     */
    private javafx.scene.paint.Paint paint;

    /**
     * Indicates whether the piece was taken by a {@link gameLogic.Player}.
     */
    private boolean isTaken;

}
