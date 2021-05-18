package controller;

import util.GameResult;
import gameLogic.Piece;
import gameLogic.Player;
import gameLogic.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import util.GsonHelper;
import java.io.*;
import java.time.LocalDate;

/**
 * Controller of the game view.
 */

@Slf4j
public class GameController {

    /**
     * GridPane that represents the board.
     */
    @FXML
    private GridPane gridPane;

    /**
     * Label that has the player1s name on it.
     */
    @FXML
    private Label player1Name;

    /**
     * Label that has player2s name on it.
     */
    @FXML
    private Label player2Name;

    /**
     * Label that has player1s score on it.
     */
    @FXML
    private Label score1;

    /**
     * Label that has player2s score on it.
     */
    @FXML
    private Label score2;

    /**
     * Label that has the winners name on it.
     */
    @FXML
    private Label winnerLabel;

    /**
     * {@link Player} number one.
     */
    private Player player1;

    /**
     * {@link Player} number two.
     */
    private Player player2;

    /**
     * {@link Board} that represents the current state of the board.
     */
    private Board board;

    /**
     * Number of turns that were taken.
     */
    private int turn;

    /**
     * Indicates the row of the last {@link Piece} that was taken.
     */
    private int lastRow = -9;

    /**
     * Indicates the column of the last {@link Piece} that was taken.
     */
    private int lastColumn = -9;

    /**
     * Initializes the game.
     */
    public void initialize(){
        board = new Board();
        turn = 1;
        drawBoard();

        log.info("Game has started");

    }

    /**
     * Initializes the players.
     * @param player1 {@link Player} number one
     * @param player2 {@link Player} number two
     */
    public void initdata(String player1, String player2){
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        log.info("{} vs {}",player1, player2);
        setNames();
    }

    /**
     * Draws the current state of the board.
     */
    private void drawBoard(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Circle circle = (Circle) gridPane.getChildren().get(i * 7 + j);
                circle.setStroke(javafx.scene.paint.Paint.valueOf("black"));
                circle.setStrokeWidth(1.0);

                if(board.getPieceAt(i,j).isTaken()){
                    circle.setVisible(false);
                } else {
                    circle.setFill(board.getPieceAt(i,j).getPaint());

                    if(isNeighbour(i, j)){
                        circle.setStroke(javafx.scene.paint.Paint.valueOf("yellow"));
                        circle.setStrokeWidth(3.0);
                    }
                }
            }
        }
    }

    /**
     * Checks whether the game is finished.
     * @return whether the game is finished
     */
    private boolean isFinished(){
        int numberOfNeighbours = 0;
        int numberOfRed = 0;
        int numberOfBlue = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Circle circle = (Circle) gridPane.getChildren().get(i * 7 + j);

                if(!board.getPieceAt(i,j).isTaken()){
                    if(isColored(board.getPieceAt(i,j), "red"))
                        numberOfRed++;

                    if(isColored(board.getPieceAt(i,j), "blue"))
                        numberOfBlue++;

                    if(isNeighbour(i, j)) {
                        circle.setStroke(javafx.scene.paint.Paint.valueOf("yellow"));
                        circle.setStrokeWidth(3.0);
                        numberOfNeighbours++;
                    }
                }
            }
        }

        return turn != 1 && numberOfNeighbours == 0 || numberOfBlue == 0 || numberOfRed == 0;
    }

    /**
     * Takes the circle from the board, if possible.
     * @param mouseEvent click on one of the circles
     * @throws IllegalArgumentException if {@code MouseEvent} fails
     */
    public void circleClicked(MouseEvent mouseEvent) throws IllegalArgumentException{
        int row = GridPane.getRowIndex((Node)mouseEvent.getSource());
        int column = GridPane.getColumnIndex((Node)mouseEvent.getSource());

        if(turn == 1 && board.getPieceAt(row, column).getPaint() == javafx.scene.paint.Paint.valueOf("green") || isNeighbour(row,column) && !isFinished()){
            log.info("turn {}: ({},{}) circle was clicked", turn, row, column);

            if(turn % 2 ==  1){
                board.takePiece(row, column, player1);
            } else if(turn % 2 == 0)
                board.takePiece(row, column, player2);

            turn++;
            lastRow = row;
            lastColumn = column;

            if(isFinished())
                finishGame();

            updateState();
            drawBoard();
        }

    }

    /**
     * Determines the winner of the game.
     * @return the winner of the game
     */
    private Player determineWinner(){
        if(player1.getNumberOfColor("blue") + player2.getNumberOfColor("blue") == 10 ||
                player1.getNumberOfColor("red") + player2.getNumberOfColor("red") == 10){

            if(board.getPieceAt(lastRow, lastColumn).getPaint() == javafx.scene.paint.Paint.valueOf("red"))
                return player1;
            if(board.getPieceAt(lastRow, lastColumn).getPaint() == javafx.scene.paint.Paint.valueOf("blue"))
                return player2;
        }

        if(player1.getNumberOfColor("red") > player2.getNumberOfColor("blue")) {
            return player1;
        }
        if(player1.getNumberOfColor("red") < player2.getNumberOfColor("blue")) {
            return player2;
        }


        return null;
    }

    /**
     * Updates the scoreboard.
     */
    private void updateState(){
        score1.setText(Integer.toString(player1.getNumberOfColor("red")));
        score2.setText(Integer.toString(player2.getNumberOfColor("blue")));
        switchPlayer();
    }

    /**
     * Switches turns on the view.
     */
    private void switchPlayer(){
        if(turn % 2 == 1){
            player1Name.setUnderline(true);
            player2Name.setUnderline(false);
        } else {
            player1Name.setUnderline(false);
            player2Name.setUnderline(true);
        }
    }

    /**
     * Switches to menu view if the Menu button is clicked.
     * @param actionEvent click on the button
     * @throws IOException if {@code ActionEvent} fails or the fxml can not be found
     */
    public void menuClicked(ActionEvent actionEvent) throws IOException {
        log.info("switching to menu view");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     * Ends the game, and saves results.
     */
    private void finishGame(){
        Player winner = determineWinner();

        if(winner == null){
            winnerLabel.setText("The game is a tie!");
            log.info("game finished with a tie");
            GameResult result = makeGameResult("tie");
            GsonHelper.appendToGson(result);
        } else if(winner.equals(player1)){
            winnerLabel.setText("Congrats " + player1.getName() + ",you won!");
            GameResult result = makeGameResult(player1.getName());
            GsonHelper.appendToGson(result);
            log.info("game is finished, {} won", winner.getName());
        } else if(winner.equals(player2)){
            winnerLabel.setText("Congrats " + player2.getName() + ",you won!");
            GameResult result = makeGameResult(player2.getName());
            GsonHelper.appendToGson(result);
            log.info("game is finished, {} won", winner.getName());
        }




    }

    /**
     * Checks if the given piece is the neighbour of the last taken one.
     * @param row the row of the board where the piece is located
     * @param column the column of the board where the piece is located
     * @return determines if the pieces are neighbours
     */
    private boolean isNeighbour(int row, int column){
        if(Math.abs(lastColumn - column) < 2 && Math.abs(lastRow - row) < 2)
            return true;

        return false;
    }

    /**
     * Determines if the given {@link Piece} is given colored.
     * @param piece the piece needs to be examined
     * @param paint the color used for the examination
     * @return whether the {@link Piece} is {@code paint}
     */
    private boolean isColored(Piece piece, String paint){
        if(piece.getPaint().equals(javafx.scene.paint.Paint.valueOf(paint))){
            return true;
        }
        return false;
    }

    /**
     * Sets the labels to show the names of the players.
     */
    private void setNames(){
        player1Name.setText(player1.getName());
        player2Name.setText(player2.getName());
        player1Name.setUnderline(true);
    }

    /**
     * Creates a {@link GameResult}.
     * @param winner the name of the winner
     * @return {@link GameResult} with the given winner
     */
    private GameResult makeGameResult (String winner){
        GameResult result = GameResult.builder().date(LocalDate.now())
                .player1(player1.getName())
                .player2(player2.getName())
                .date(LocalDate.now())
                .winner(winner)
                .turns(turn)
                .build();
        return result;
    }
}