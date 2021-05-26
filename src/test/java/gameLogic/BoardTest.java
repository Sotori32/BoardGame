package gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    Board board = new Board(null, null);
    Piece red = new OnePiece(board);
    Piece blue = new TwoPiece(board);
    Piece green = new NeutralPiece(board);

    @Test
    void testTakePiece() {

        Player player = new Player(new OnePiece(null));
        player.setName("test");
        assertThrows(IllegalArgumentException.class, () -> board.takePiece(-1,2,player));
        assertThrows(IllegalArgumentException.class, () -> board.takePiece(7,2,player));
        assertThrows(IllegalArgumentException.class, () -> board.takePiece(0,-1,player));
        assertThrows(IllegalArgumentException.class, () -> board.takePiece(0,9,player));
    }

    @Test
    void testGetPieceAt() {
        assertTrue(green.equals(board.getPieceAt(0, 0)));
        assertTrue(green.equals(board.getPieceAt(3, 3)));
        assertTrue(blue.equals(board.getPieceAt(0,1)));
        assertTrue(red.equals(board.getPieceAt(1,0)));
        assertTrue(blue.equals(board.getPieceAt(0,4)));
        assertTrue(red.equals(board.getPieceAt(4,0)));

    }
}