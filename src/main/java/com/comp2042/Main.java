package com.comp2042;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * The {@code Main} class serves as the entry point for the TetrisJFX application.
 * It initializes the JavaFX application, loads the FXML layout, and sets up
 * the primary stage for the game.
 */

public class Main extends Application {

    @Override
    /** The start method is the main entry point for all JavaFX applications.
     * It sets up the primary stage with the game layout defined in FXML.
     *
     * @param primaryStage the primary stage for this application
     * @throws Exception if loading the FXML fails
     */
    public void start(Stage primaryStage) throws Exception {

        URL location = getClass().getClassLoader().getResource("gameLayout.fxml");
        FXMLLoader loader = new FXMLLoader(location);

        Parent root = loader.load();

        GuiController c = loader.getController();  // Now FXML creates it

        primaryStage.setTitle("TetrisJFX");
        Scene scene = new Scene(root, 650, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    /** The main method is the entry point of the application.
     * It launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
