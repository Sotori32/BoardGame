package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Controller of the menu view.
 */
@Slf4j
public class MenuController {


    /**
     * Label that becomes visible if errors happen.
     */
    @FXML
    private Label errorLabel;

    /**
     * TextField for player1s name.
     */
    @FXML
    private TextField player1;

    /**
     * TextField for player2s name.
     */
    @FXML
    private TextField player2;

    /**
     * Switches to game view if the Start button is clicked.
     * @param actionEvent Start button clicked
     * @throws IOException if {@code ActionEvent} fails or the fxml can not be found
     */
    public void startClicked(ActionEvent actionEvent) throws IOException {
        if (player1.getText().isEmpty() || player2.getText().isEmpty()) {
            errorLabel.setVisible(true);
            errorLabel.setText("Please enter a username");
        }else if(player1.getText().equals(player2.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("You can't use the same username");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<GameController>getController().initdata(player1.getText(), player2.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }
    }

    /**
     * Switches to toplist view if the Toplist button is clicked.
     * @param actionEvent Toplist button clicked
     * @throws IOException if {@code ActionEvent} fails or the fxml can not be found
     */
    public void toplistClicked(ActionEvent actionEvent) throws IOException {
        log.info("switching to toplist view");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/toplist.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
}



