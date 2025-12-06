package com.comp2042;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Optional;


public class StartMenu extends StackPane {

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    private final Button startGameButton;
    private final Button exitGameButton;

    private Runnable onStartGame;
    private Runnable onQuitGame;
    private Difficulty difficultyselected = Difficulty.EASY;
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;


    public StartMenu() {
        this.getStyleClass().add("startMenuBackground");

        Label titleLabel = new Label("Tetris");
        titleLabel.getStyleClass().add("startMenuTitle");

        Label difficultyLabel = new Label("Select Difficulty:");
        difficultyLabel.getStyleClass().add("difficultyLabel");

        easyButton = new Button("Easy");
        easyButton.getStyleClass().addAll("unified-button", "difficulty-button");
        easyButton.setOnAction(e -> selectDifficulty(Difficulty.EASY));

        mediumButton = new Button("Medium");
        mediumButton.getStyleClass().addAll("unified-button", "difficulty-button");
        mediumButton.setOnAction(e -> selectDifficulty(Difficulty.MEDIUM));

        hardButton = new Button("Hard");
        hardButton.getStyleClass().addAll("unified-button", "difficulty-button");
        hardButton.setOnAction(e -> selectDifficulty(Difficulty.HARD));

        HBox difficultyButtons = new HBox(10, easyButton, mediumButton, hardButton);
        difficultyButtons.setAlignment(Pos.CENTER);


        startGameButton = new Button("Start Game");
        startGameButton.getStyleClass().add("unified-button");
        startGameButton.setOnAction(event -> {
            if (onStartGame != null) {
                onStartGame.run();
            }
        });


        exitGameButton = new Button("Exit Game");
        exitGameButton.getStyleClass().add("unified-button");
        exitGameButton.setOnAction(event -> {
            handleExitGame();
        });


        VBox buttonContainer = new VBox(15, startGameButton, exitGameButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getStyleClass().add("startMenuButtonContainer");

        VBox menuBox = new VBox(40, titleLabel, difficultyLabel, difficultyButtons, buttonContainer);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.getStyleClass().add("startMenuBox");

        this.getChildren().add(menuBox);
    }

    private void selectDifficulty(Difficulty difficulty) {
        this.difficultyselected = difficulty;
        easyButton.getStyleClass().remove("selected");
        mediumButton.getStyleClass().remove("selected");
        hardButton.getStyleClass().remove("selected");

        switch (difficulty) {
            case EASY:
                easyButton.getStyleClass().add("selected");
                break;
            case MEDIUM:
                mediumButton.getStyleClass().add("selected");
                break;
            case HARD:
                hardButton.getStyleClass().add("selectedDifficultyButton");
                break;
        }
        System.out.println("Selected Difficulty: " + difficultyselected);
    }

    public Difficulty getSelectedDifficulty() {
        return difficultyselected;
    }

    public void setOnStartGame(Runnable onStartGame) {
        this.onStartGame = onStartGame;
    }

    public void setOnQuitGame(Runnable onQuitGame) {
        this.onQuitGame = onQuitGame;
    }


    private void handleExitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Game");
        alert.setHeaderText("Are you sure you want to quit?");
        alert.setContentText("Any unsaved progress will be lost.");

        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            if (onQuitGame != null) {
                onQuitGame.run();
            }
        }
    }
}




