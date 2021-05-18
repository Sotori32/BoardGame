package gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    Board board = new Board();
    Piece red = new Piece(javafx.scene.paint.Paint.valueOf("red"), false);
    Piece blue = new Piece(javafx.scene.paint.Paint.valueOf("blue"), false);
    Piece green = new Piece(javafx.scene.paint.Paint.valueOf("green"), false);

    @Test
    void testTakePiece() {

        Player player = new Player("test");
        assertThrows(IllegalArgumentException.class, () -> board.takePiece(-1,2,player));
        assertThrows(IllegalArgumentException.class, () -> board.takePiece(7,2,player));
        assertThrows(IllegalArgumentException.class, () -> board.takePiece(0,-1,player));
        assertThrows(IllegalArgumentException.class, () -> board.takePiece(0,9,player));
    }

    @Test
    void testGetPieceAt() {
        assertEquals(green, board.getPieceAt(0,0));
        assertEquals(green, board.getPieceAt(3,3));
        assertEquals(blue, board.getPieceAt(0,1));
        assertEquals(red, board.getPieceAt(1,0));
        assertEquals(blue, board.getPieceAt(0,4));
        assertEquals(red, board.getPieceAt(4,0));

    }
}