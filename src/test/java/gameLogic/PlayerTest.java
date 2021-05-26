package gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player player = new Player(new OnePiece(null));
    Piece red = new OnePiece(null);
    Piece blue = new TwoPiece(null);
    Piece green = new NeutralPiece(null);

    @Test
    void testGetNumberOfColor() {
        assertEquals(0, player.getScore());


        player.addPiece(red);
        player.addPiece(red);
        player.addPiece(red);
        player.addPiece(green);
        player.addPiece(green);
        player.addPiece(blue);

        assertEquals(3, player.getScore());

        player.setOwnPiece(new TwoPiece(null));
        assertEquals(1, player.getScore());

        player.setOwnPiece(new NeutralPiece(null));
        assertEquals(2, player.getScore());
    }
}