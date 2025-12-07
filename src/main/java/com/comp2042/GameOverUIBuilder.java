package com.comp2042;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * GameOverUIBuilder is responsible for adding interactive buttons to the GameOverPanel.
 * It provides functionality to start a new game or quit to the main menu.
 */

public class GameOverUIBuilder {
    private final GameOverPanel gameOverPanel;
    private final Runnable onNewGame;
    private final Runnable onQuit;

    /**
     * Constructs a GameOverUIBuilder with the specified GameOverPanel and button actions.
     *
     * @param panel     The GameOverPanel to which buttons will be added.
     * @param onNewGame A Runnable to execute when the "New Game" button is clicked.
     * @param onQuit    A Runnable to execute when the "Quit to Menu" button is clicked.
     */
    public GameOverUIBuilder(GameOverPanel panel, Runnable onNewGame, Runnable onQuit) {
        this.gameOverPanel = panel;
        this.onNewGame = onNewGame;
        this.onQuit = onQuit;
    }

    /**
     * Adds "New Game" and "Quit to Menu" buttons to the GameOverPanel.
     * The buttons are centered at the bottom of the panel.
     */
    public void addGameOverButtons() {
        // Create buttons
        Button newGameBtn = new Button("New Game");
        newGameBtn.getStyleClass().add("unified-button");
        newGameBtn.setOnAction(e -> {
            // Hide panel and remove buttons
            gameOverPanel.setVisible(false);
            gameOverPanel.setManaged(false);
            gameOverPanel.setBottom(null);
            onNewGame.run();
        });

        Button quitBtn = new Button("Quit to Menu");
        quitBtn.getStyleClass().add("unified-button");
        quitBtn.setOnAction(e -> {
            // Hide panel and remove buttons
            gameOverPanel.setVisible(false);
            gameOverPanel.setManaged(false);
            gameOverPanel.setBottom(null);
            onQuit.run();
        });

        // Create button container
        HBox buttonBox = new HBox(20, newGameBtn, quitBtn);
        buttonBox.setAlignment(Pos.CENTER);

        // Add buttons to the bottom of the GameOverPanel
        gameOverPanel.setBottom(buttonBox);
        gameOverPanel.setAlignment(buttonBox, Pos.CENTER);
        BorderPane.setMargin(buttonBox, new Insets(20, 0, 0, 0));
    }
}