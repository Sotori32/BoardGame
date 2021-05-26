package controller;

import gameLogic.*;
import javafx.scene.paint.Color;
import util.GameResult;
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

    Drawer drawer;

    /**
     * Initializes the game.
     */
    public void initialize(){
        player1 = new Player(new OnePiece(null));
        player2 = new Player(new TwoPiece(null));

        board = new Board(player1, player2);

        turn = 1;
        drawer = new Drawer(gridPane);
        drawBoard();

        log.info("Game has started");

    }

    /**
     * Initializes the players.
     * @param player1 {@link Player} number one
     * @param player2 {@link Player} number two
     */
    public void initdata(String player1, String player2){
        log.info("{} vs {}",player1, player2);

        this.player1.setName(player1);
        this.player2.setName(player2);

        setNames();
    }

    /**
     * Draws the current state of the board.
     */
    private void drawBoard(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Circle circle = (Circle) gridPane.getChildren().get(i * 7 + j);
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(1.0);

                Piece piece = board.getPieceAt(i, j);

                if(piece.isTaken()) {
                    circle.setVisible(false);
                } else
                    drawer.draw(piece, circle);
            }
        }
    }

    /**
     * Takes the circle from the board, if possible.
     * @param mouseEvent click on one of the circles
     * @throws IllegalArgumentException if {@code MouseEvent} fails
     */
    public void circleClicked(MouseEvent mouseEvent) throws IllegalArgumentException{
        int row = GridPane.getRowIndex((Node)mouseEvent.getSource());
        int column = GridPane.getColumnIndex((Node)mouseEvent.getSource());

        if(board.getPieceAt(row, column).isClickable() && !board.isFinished()){
            log.info("turn {}: ({},{}) circle was clicked", turn, row, column);

            if(turn % 2 ==  1){
                board.takePiece(row, column, player1);
            } else if(turn % 2 == 0)
                board.takePiece(row, column, player2);

            turn++;

            if(board.isFinished())
                finishGame();

            updateState();
            drawBoard();
        }

    }

    /**
     * Updates the scoreboard.
     */
    private void updateState(){
        score1.setText(Integer.toString(player1.getScore()));
        score2.setText(Integer.toString(player2.getScore()));
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
        Player winner = board.determineWinner();

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