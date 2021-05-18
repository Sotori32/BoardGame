package controller;

import util.GameResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import util.GsonHelper;
import util.ToplistClass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Controller of the toplist view.
 */
@Slf4j
public class ToplistController {

    /**
     * TableView that contains the top five players.
     */
    @FXML
    TableView<ToplistClass> tableview;

    /**
     * TableColumn that contains the top five players names.
     */
    @FXML
    TableColumn<ToplistClass, String> player;

    /**
     * TableColumn that contains the top five players number of wins.
     */
    @FXML
    TableColumn<ToplistClass, Integer> wins;

    /**
     * Fills the {@code Tableview} with the five best players.
     */
    public void initialize(){
        ArrayList<ToplistClass> fiveBest = findFiveBest();

        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        wins.setCellValueFactory(new PropertyValueFactory<>("wins"));


        ObservableList<ToplistClass> observableResult = FXCollections.observableArrayList();


        observableResult.addAll(fiveBest);


        tableview.setItems(observableResult);
    }

    /**
     * Switches to menu view if the Menu button is clicked.
     * @param actionEvent click on the button
     * @throws Exception if {@code ActionEvent} fails or the fxml can not be found
     */
    public void menuClicked(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("switching to menu view");
    }

    /**
     * Finds the five best players.
     * @return the five best players.
     */
    private ArrayList findFiveBest(){
        ArrayList<GameResult> results = GsonHelper.deserializeGson();
        ArrayList<ToplistClass> players = new ArrayList<>();
        for(GameResult result: results){
            if(result.getWinner().equals("tie"))
                continue;
            if(!contains(players, result.getWinner())){
                log.info("{} was added to the list",result.getWinner());
                if(result.getWinner().equals(result.getPlayer1()))
                    players.add(new ToplistClass(result.getWinner(),1));
                if(result.getWinner().equals(result.getPlayer2()))
                    players.add(new ToplistClass(result.getWinner(),1));
            }
            if(contains(players, result.getWinner())){
                addWin(players, result.getWinner());
            }
        }

        Collections.sort(players, Comparator.comparingInt(ToplistClass::getWins));
        Collections.reverse(players);
        if(players.size() < 6)
            return players;
        else
            players = new ArrayList<ToplistClass>(players.subList(0,5));
            return players;

    }

    /**
     * Checks if the given list of players contains the named player.
     * @param players the list of players
     * @param name the name of the searched player
     * @return whether the list contains the player or not
     */
    private boolean contains(ArrayList<ToplistClass> players, String name) {
        for (ToplistClass  player: players) {
            if(player.getPlayer().equals(name))
                return true;
        }
        return false;
    }

    /**
     * Adds a win to the given player.
     * @param toplist the list where the player is contained
     * @param name name of the player
     */
    private void addWin(ArrayList<ToplistClass> toplist, String name){
        for(ToplistClass c: toplist){
            if(c.getPlayer().equals(name))
                c.addWin();
        }
    }
}

