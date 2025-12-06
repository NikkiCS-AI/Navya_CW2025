package com.comp2042;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
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


    public static void main(String[] args) {
        launch(args);
    }
}
