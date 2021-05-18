package gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player player = new Player("test");
    Piece red = new Piece(javafx.scene.paint.Paint.valueOf("red"), false);
    Piece blue = new Piece(javafx.scene.paint.Paint.valueOf("blue"), false);
    Piece green = new Piece(javafx.scene.paint.Paint.valueOf("green"), false);

    @Test
    void testGetNumberOfColor() {
        assertEquals(0, player.getNumberOfColor("red"));
        assertEquals(0, player.getNumberOfColor("green"));
        assertEquals(0, player.getNumberOfColor("blue"));


        player.addPiece(red);
        player.addPiece(red);
        player.addPiece(red);
        player.addPiece(green);
        player.addPiece(green);
        player.addPiece(blue);

        assertEquals(3, player.getNumberOfColor("red"));
        assertEquals(2, player.getNumberOfColor("green"));
        assertEquals(1, player.getNumberOfColor("blue"));
    }
}