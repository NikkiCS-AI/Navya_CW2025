package com.comp2042;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiController implements Initializable {

    static final int BRICK_SIZE = 20;

    private final GridRenderer gridRenderer = new GridRenderer();
    private final BrickRenderer brickRenderer = new BrickRenderer();
    private final GameLoopManager gameLoop = new GameLoopManager();
    private final GameStateManager gameState = new GameStateManager();

    @FXML private Label clearedRows;
    @FXML private Label bindScore;

    @FXML
    GridPane gamePanel;
    @FXML private GridPane brickPanel;
    @FXML private GridPane previewPanel;
    @FXML private GridPane holdPanel;

    @FXML private Group groupNotification;
    @FXML private GameOverPanel gameOverPanel;
    @FXML private Button pauseGame;
    @FXML private Label holdLabel;

    InputEventListener eventListener;
    private KeyInputHandler inputHandler;

    Rectangle[][] displayMatrix;
    Rectangle[][] rectangles;

    private Timeline timeLine;
    private int clearedRowCount = 0;

    private final AudioManager audioManager = new AudioManager();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFont();
        createGamePanel();

        inputHandler = new KeyInputHandler(this);

        gamePanel.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            inputHandler.handleMovementKey(code);
            inputHandler.handleSystemKey(code);
            event.consume();
        });

        gameOverPanel.setVisible(false);

        Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(0.9);
        reflection.setTopOffset(-12);

        Platform.runLater(audioManager::playBackgroundMusic);
    }

    private void createGamePanel() {
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();
    }

    private void loadFont() {
        Font.loadFont(getClass().getClassLoader().getResource("digital.ttf").toExternalForm(), 38);
    }



    public boolean isPaused() {
        return gameState.isPaused();
    }

    public boolean isGameOver() {
        return gameState.isGameOver();
    }


    public void initGameView(int[][] boardMatrix, ViewData brick) {

        brickPanel.setLayoutX(
                gamePanel.getLayoutX()
                        + brick.getxPosition() * (brickPanel.getVgap() + BRICK_SIZE)
        );

        brickPanel.setLayoutY(
                -42 + gamePanel.getLayoutY()
                        + brick.getyPosition() * (brickPanel.getHgap() + BRICK_SIZE)
        );

        gridRenderer.createGrid(boardMatrix, brick, this);

        gameLoop.start(() -> performMoveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD)));
    }

    public void updateNextBrick(ViewData nextBrick) {
        BrickRenderer.updatePreviewPanel(previewPanel, nextBrick);
    }

    public void refreshBrick(ViewData brick) {
        brickRenderer.refreshBrick(brick, rectangles, brickPanel, gamePanel, gameState.isPaused());
    }

    public void refreshGameBackground(int[][] board) {
        brickRenderer.refreshGameBackground(board, displayMatrix);
    }

    public void updateHoldDisplay(int[][] holdShape) {
        brickRenderer.updateHoldPanel(holdPanel, holdShape);
    }

    public void performMoveDown(MoveEvent event) {
        if (!gameState.isPaused()) {
            DownData data = eventListener.onDownEvent(event);

            if (data.getClearRow() != null && data.getClearRow().getLinesRemoved() > 0) {
                audioManager.playLineClearSound();
                gameState.addClearedRows(data.getClearRow().getLinesRemoved());

                Platform.runLater(() ->
                        clearedRows.setText("Cleared Rows: " + gameState.getClearedRowCount())
                );
            }

            refreshBrick(data.getViewData());
        }

        gamePanel.requestFocus();
    }

    public void setEventListener(InputEventListener listener) {
        this.eventListener = listener;
    }

    public void bindScore(IntegerProperty scoreProperty) {
        if (bindScore != null) {
            bindScore.textProperty().bind(scoreProperty.asString("Score: %d"));
        }
    }

    public void gameOver() {
        gameLoop.stop();
        gameState.setGameOver(true);
        gameOverPanel.setVisible(true);

        audioManager.stopBackgroundMusic();
        audioManager.playGameOverSound();
    }

    public void newGame(javafx.event.ActionEvent actionEvent) {
        gameLoop.stop();
        gameState.reset();
        clearedRowCount = 0;
        clearedRows.setText("Cleared Rows: 0");

        gameOverPanel.setVisible(false);

        eventListener.createNewGame();
        audioManager.playBackgroundMusic();

        gameLoop.start(() -> performMoveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD)));
    }


    public void pauseGame(javafx.event.ActionEvent actionEvent) {
        if (!gameState.isPaused()) {
            gameLoop.pause();
            gameState.setPaused(true);
            audioManager.stopBackgroundMusic();
            pauseGame.setText("Resume");
        } else {
            gameLoop.resume();
            gameState.setPaused(false);
            audioManager.playBackgroundMusic();
            pauseGame.setText("Pause");
        }

        gamePanel.requestFocus();
    }


    public void clearedRows(int linesCleared) {
        clearedRowCount += linesCleared;
        Platform.runLater(() ->
                clearedRows.setText("Cleared Rows: " + clearedRowCount)
        );
    }

    public GridPane getbrickPanel() {
        return brickPanel;
    }
}
