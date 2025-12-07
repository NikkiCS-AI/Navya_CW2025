package com.comp2042;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * GameOverPanel represents the UI panel displayed when the game is over.
 * It shows a "GAME OVER" message and the player's score.
 */

public class GameOverPanel extends BorderPane {

    private final Label gameOverLabel;
    private final Label scoreLabel;

    /** Constructs a GameOverPanel with default labels.
     */
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

    /** Sets the score to be displayed on the panel.
     *
     * @param score The score as a String.
     */
    public void setScore(String score) {
        scoreLabel.setText("Score: " + score);
    }

    /** Sets the score to be displayed on the panel.
     *
     * @param score The score as an integer.
     */
    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

}