package com.comp2042;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GameOverPanel extends BorderPane {

    private final Label gameOverLabel;
    private final Label scoreLabel;

    public GameOverPanel() {
        // Game Over label
        gameOverLabel = new Label("GAME OVER");
        gameOverLabel.getStyleClass().add("gameOverTitle");

        // Score label
        scoreLabel = new Label("Score: 0");
        scoreLabel.getStyleClass().add("gameOverScore");

        // Main container
        VBox container = new VBox(20, gameOverLabel, scoreLabel);
        container.setAlignment(Pos.CENTER);

        setCenter(container);
    }

    public void setScore(String score) {
        scoreLabel.setText("Score: " + score);
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

}