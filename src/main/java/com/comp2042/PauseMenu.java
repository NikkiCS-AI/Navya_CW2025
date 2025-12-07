package com.comp2042;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * PauseMenu represents a pause menu overlay in the game.
 * It contains buttons for resuming the game, starting a new game, and quitting to the main menu.
 */

public class PauseMenu extends StackPane {

    private final VBox menuBox;
    private final Button newGameButton;
    private final Button resumeButton;
    private final Button quitButton;

    /** Constructs a PauseMenu with buttons and styling.
     */
    public PauseMenu() {
        this.getStyleClass().add("pauseMenuContainer");
        this.setAlignment(Pos.CENTER);
        this.setVisible(false);

        Rectangle bg = new Rectangle(300, 250);
        bg.getStyleClass().add("pauseMenuBackground");

        Label title = new Label("Game Paused");
        title.getStyleClass().add("pauseMenuTitle");

         resumeButton = new Button("Resume");
        resumeButton.getStyleClass().addAll("unified-button");

         newGameButton = new Button("New Game");
        newGameButton.getStyleClass().addAll("unified-button");

         quitButton = new Button("Quit to Menu");
        quitButton.getStyleClass().addAll("unified-button");

        VBox buttonContainer = new VBox(15, resumeButton, newGameButton, quitButton);
        buttonContainer.setAlignment(Pos.CENTER);

        menuBox = new VBox(15, title, buttonContainer);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setMaxSize(280, 230);

        this.getChildren().addAll(bg, menuBox);
    }

    /** Getter for the resume button.
     * @return the resume button
     */
    public Button getResumeButton() {
        return resumeButton;
    }

    /** Getter for the new game button.
     * @return the new game button
     */
    public Button getNewGameButton() {
        return newGameButton;
    }

    /** Getter for the quit button.
     * @return the quit button
     */
    public Button getQuitButton() {
        return quitButton;
    }

    /** Shows the pause menu.
     */
    public void showMenu() {
        this.setVisible(true);
        this.setManaged(true);
    }

    /** Hides the pause menu.
     */
    public void hideMenu() {
        this.setVisible(false);
        this.setManaged(false);
    }
}