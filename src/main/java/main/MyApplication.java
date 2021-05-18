package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class that sets up the window and starts the application.
 */
public class MyApplication extends Application {

    /**
     * Sets up the window, and switches to menu view.
     * @param primaryStage the window of the application
     * @throws Exception the fxml can not be found
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        primaryStage.setTitle("BoardGame");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}