package gameLogic;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Class that represents the board that the game is played on.
 */
public class Board {
    /**
     * Indicates the row of the last {@link Piece} that was taken.
     */
    private int lastRow = -9;

    /**
     * Indicates the column of the last {@link Piece} that was taken.
     */
    private int lastColumn = -9;

    /**
     * {@link Player} number one.
     */
    private Player player1;

    /**
     * {@link Player} number two.
     */
    private Player player2;

    int numberOfOne = 10;
    int numberOfTwo = 10;

    /**
     *The current state of the board.
     */
    private  Piece[][] board;

    /**
     * The initial state of the board.
     */
    private final Piece[][] initial = {
        {makeGreen(), makeBlue(), makeBlue(), makeBlue(), makeBlue(), makeBlue(), makeGreen()},
        {makeRed(), makeGreen(), makeGreen(), makeGreen(), makeGreen(), makeGreen(),makeRed()},
        {makeRed(), makeGreen(), makeGreen(), makeGreen(), makeGreen(), makeGreen(),makeRed()},
        {makeRed(), makeGreen(), makeGreen(), makeGreen(), makeGreen(), makeGreen(),makeRed()},
        {makeRed(), makeGreen(), makeGreen(), makeGreen(), makeGreen(), makeGreen(),makeRed()},
        {makeRed(), makeGreen(), makeGreen(), makeGreen(), makeGreen(), makeGreen(),makeRed()},
        {makeGreen(), makeBlue(), makeBlue(), makeBlue(), makeBlue(), makeBlue(), makeGreen()},
    };

    /**
     * Constructor that creates a board with the {@link #initial} state.
     * @param p1 first player
     * @param p2 second player
     */
    public Board(Player p1, Player p2){
        player1 = p1;
        player2 = p2;
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
        board[row][column].take();
        player.addPiece(board[row][column]);

        if (lastColumn != -1 && lastRow != -9)
            setClickableNext(lastRow, lastColumn, false);

        lastRow = row;
        lastColumn = column;

        setClickableNext(lastRow, lastColumn, true);
    }

    private void setClickableNext(int row, int column, boolean value) {
        for (int i = 0; i < 7; ++i)
            for (int j = 0; j < 7; ++j)
                board[i][j].setClickable(false);

        if (row - 1 >= 0 && column - 1 >= 0) //left upper corner
            board[row - 1][column - 1].setClickable(value);

        if (row - 1 >= 0) //upper edge
            board[row - 1][column].setClickable(value);

        if (column - 1 >= 0) //left edge
            board[row][column - 1].setClickable(value);

        if (row + 1 < 7 && column - 1 >= 0) //left lower corner
            board[row + 1][column - 1].setClickable(value);

        if (row + 1 < 7) //lower edge
            board[row + 1][column].setClickable(value);

        if (row + 1 < 7 && column + 1 < 7) //right lower corner
            board[row + 1][column + 1].setClickable(value);

        if (column + 1 < 7) //right edge
            board[row][column + 1].setClickable(value);

        if (row - 1 >= 0 && column + 1 < 7) //right upper corner
            board[row - 1][column + 1].setClickable(value);
    }

    /**
     * Checks whether the game is finished.
     * @return whether the game is finished
     */
    public boolean isFinished(){
        int numberOfClickable = 0;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {

                Piece tmp = getPieceAt(i,j);

                if(!tmp.isTaken()){
                    if(tmp.isClickable()) {
                        numberOfClickable++;
                    }
                }
            }
        }

        return (lastRow != -9 && lastColumn != -9) && (numberOfClickable == 0 || numberOfTwo == 0 || numberOfOne == 0);
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
        return new OnePiece(this);
    }

    /**
     * Creates a green {@link Piece}.
     * @return the created piece
     */
    private Piece makeGreen(){
        return new NeutralPiece(this);
    }

    /**
     * Creates a blue {@link Piece}.
     * @return the created piece
     */
    private Piece makeBlue(){
        return new TwoPiece(this);
    }

    /**
     * Reduces the number of red pieces by one.
     */
    public void reduceOnes() {
        --numberOfOne;
    }

    /**
     * Reduces the number of blue pieces by one.
     */
    public void reduceTwos() {
        --numberOfTwo;
    }

    /**
     * Determines the winner of the game.
     * @return the winner of the game
     */
    public Player determineWinner(){
        if (numberOfOne == 0) {
            return player1.getOwnPiece().equals(new OnePiece(null)) ? player1 : player2;
        }
        if (numberOfTwo == 0)
            return player1.getOwnPiece().equals(new TwoPiece(null)) ? player1 : player2;

        if(player1.getScore() > player2.getScore()) {
            return player1;
        }
        if(player1.getScore() < player2.getScore()) {
            return player2;
        }

        return null;
    }
}
