package gameLogic;

/**
 * Class that represents the board that the game is played on.
 */
public class Board {
    /**
     *The current state of the board.
     */
    private  Piece[][] board;

    /**
     * The initial state of the board.
     */
    private  final Piece[][] initial = {
        {makeGreen(), makeBlue(), makeBlue(), makeBlue(), makeBlue(), makeBlue(), makeGreen()},
        {makeRed(), makeGreen(), makeGreen(), makeGreen(), makeGreen(), makeGreen(),makeRed()},
        {makeRed(), makeGreen(), makeGreen(), makeGreen(), makeGreen(), makeGreen(),makeRed()},
        {makeRed(), makeGreen(), makeGreen(), makeGreen(), makeGreen(), makeGreen(),makeRed()},
        {makeRed(), makeGreen(), makeGreen(), makeGreen(), makeGreen(), makeGreen(),makeRed()},
        {makeRed(), makeGreen(), makeGreen(), makeGreen(), makeGreen(), makeGreen(),makeRed()},
        {makeGreen(), makeBlue(), makeBlue(), makeBlue(), makeBlue(), makeBlue(), makeGreen()},
    };

    /**
     *Constructor that creates a board with the {@link #initial} state.
     */
    public Board(){
        board = initial;
    }

    /**
     * Takes a piece from the board, and adds it to a {@link Player}.
     * @param row the row of the board where the piece is located
     * @param column the column of the board where the piece is located
     * @param player the player that takes the piece
     * @throws IllegalArgumentException if row or column is out of bounds
     */
    public void takePiece(int row, int column, Player player){
        if(row < 0 || row > 6 || column < 0 || column > 6)
            throw new IllegalArgumentException();
        board[row][column].setTaken(true);
        player.addPiece(board[row][column]);

    }

    /**
     * Returns the place at the given location.
     * @param row the row of the board where the piece is located
     * @param column the column of the board where the piece is located
     * @return the piece on the given location
     */
    public Piece getPieceAt(int row, int column){
        return board[row][column];
    }

    /**
     * Creates a red {@link Piece}.
     * @return the created piece
     */
    private Piece makeRed(){
        return new Piece(javafx.scene.paint.Paint.valueOf("red"), false);
    }

    /**
     * Creates a green {@link Piece}.
     * @return the created piece
     */
    private Piece makeGreen(){
        return new Piece(javafx.scene.paint.Paint.valueOf("green"), false);
    }

    /**
     * Creates a blue {@link Piece}.
     * @return the created piece
     */
    private Piece makeBlue(){
        return new Piece(javafx.scene.paint.Paint.valueOf("blue"), false);
    }



}
