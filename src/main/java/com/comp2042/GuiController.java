package com.comp2042;

import javafx.animation.Timeline;
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

public class GuiController implements Initializable, GameMovementInterface {
    static final int BRICK_SIZE = 20;

    private final GridRenderer gridRenderer;
    private final BrickRenderer brickRenderer;
    private final GameLoopInterface gameLoop;
    private final GameStateInterface gameState;
    private final AudioManagerInterface audioManager;

    private RowsClearedEffectsHandler rowsEffectsHandler;
    private GameOverHandler gameOverHandler;

    @FXML private Label clearedRows;
    @FXML private Label bindScore;

    @FXML GridPane gamePanel;
    @FXML private GridPane brickPanel;
    @FXML private GridPane previewPanel;
    @FXML private GridPane holdPanel;

    @FXML private Group groupNotification;
    @FXML private GameOverPanel gameOverPanel;
    @FXML private Button pauseGame;

    InputEventListener eventListener;
    private KeyInputHandler inputHandler;

    Rectangle[][] displayMatrix;
    Rectangle[][] brickRectangles;

    private GameController gameController;

    public GuiController() {
        this.gridRenderer = new GridRenderer();
        this.brickRenderer = new BrickRenderer();
        this.gameLoop = new GameLoopManager();
        this.gameState = new GameStateManager();
        this.audioManager = new AudioManager();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFont();
        setupHelpers();
        createGamePanel();
        setupInputHandler();
        setupReflection();

        gameOverPanel.setVisible(false);
        gamePanel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8);");
    }

    private void setupHelpers() {
        rowsEffectsHandler = new RowsClearedEffectsHandler(audioManager, gameState, clearedRows);
        gameOverHandler = new GameOverHandler(audioManager, gameLoop, gameState, gameOverPanel);
    }

    private void setupInputHandler() {
        inputHandler = new KeyInputHandler(this);

        // Set up key handlers
        gamePanel.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            inputHandler.handleMovementKey(code);
            inputHandler.handleSystemKey(code);
            event.consume();
        });
    }

    private void setupReflection() {
        Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(0.9);
        reflection.setTopOffset(-12);
    }

    private void createGamePanel() {
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();

        // Set grid lines for debugging
        gamePanel.setGridLinesVisible(false); // Set to true for debugging
    }

    private void loadFont() {
        try {
            Font.loadFont(getClass().getClassLoader().getResource("digital.ttf").toExternalForm(), 38);
        } catch (Exception e) {
            System.err.println("Could not load font: " + e.getMessage());
        }
    }

    public boolean isPaused() { return gameState.isPaused(); }
    public boolean isGameOver() { return gameState.isGameOver(); }

    public void initGame() {
        gameController = new GameController(this);
        audioManager.playBackgroundMusic();

        // Debug info
        System.out.println("\n=== GuiController.initGame ===");
        System.out.println("Game panel size: " + gamePanel.getPrefWidth() + "x" + gamePanel.getPrefHeight());
        System.out.println("Visible rows: " + GridRenderer.VISIBLE_ROWS);
        System.out.println("Visible start row: " + GridRenderer.VISIBLE_START_ROW);
    }

    public void initGameView(int[][] boardMatrix, ViewData brick) {
        System.out.println("\n=== initGameView ===");
        System.out.println("Board matrix: " + boardMatrix.length + "x" + boardMatrix[0].length);

        gridRenderer.createGrid(boardMatrix, brick, this);

        gameLoop.start(() -> performMoveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD)));
    }

    public void updateNextBrick(ViewData nextBrick) {
        BrickRenderer.updatePreviewPanel(previewPanel, nextBrick);
    }

    public void refreshBrick(ViewData brick) {
        brickRenderer.refreshBrick(brick, brickRectangles, brickPanel, gamePanel, gameState.isPaused());
    }

    public void refreshGameBackground(int[][] board) {
        brickRenderer.refreshGameBackground(board, displayMatrix);
    }

    public void updateHoldDisplay(int[][] holdShape) {
        brickRenderer.updateHoldPanel(holdPanel, holdShape);
    }

    public void performMoveDown(MoveEvent event) {
        if (gameState.isPaused()) {
            gamePanel.requestFocus();
            return;
        }

        DownData data = eventListener.onDownEvent(event);

        rowsEffectsHandler.apply(data.getClearRow());

        refreshBrick(data.getViewData());

        debugBrickInfo();

        gamePanel.requestFocus();
    }

    public void bindScore(IntegerProperty scoreProperty) {
        if (bindScore != null) {
            bindScore.textProperty().unbind();
            bindScore.textProperty().bind(scoreProperty.asString("Score: %d"));
        }
    }

    public void gameOver() {
        gameOverHandler.showGameOver();
    }

    public void newGame(javafx.event.ActionEvent actionEvent) {
        gameOverHandler.resetForNewGame();

        clearedRows.setText("Cleared Rows: 0");

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

    public GridPane getbrickPanel() { return brickPanel; }

    public void setEventListener(InputEventListener listener) {
        this.eventListener = listener;
    }

    @Override
    public void softDrop() {
        performMoveDown(new MoveEvent(EventType.DOWN, EventSource.USER));
    }

    @Override
    public void moveLeft() {
        refreshBrick(eventListener.onLeftEvent(new MoveEvent(EventType.LEFT, EventSource.USER)));
    }

    @Override
    public void moveRight() {
        refreshBrick(eventListener.onRightEvent(new MoveEvent(EventType.RIGHT, EventSource.USER)));
    }

    @Override
    public void rotate() {
        refreshBrick(eventListener.onRotateEvent(new MoveEvent(EventType.ROTATE, EventSource.USER)));
    }

    @Override
    public void hold() {
        eventListener.onHoldEvent();
    }

    @Override
    public void newGame() {
        newGame(null);
    }

    public void debugBrickInfo() {
        if (eventListener instanceof GameController) {
            ViewData brick = ((GameController) eventListener).getCurrentViewData();
            if (brick != null) {
                System.out.println("\n=== GUI DEBUG ===");
                System.out.println("Brick position: X=" + brick.getxPosition() + ", Y=" + brick.getyPosition());
                System.out.println("Brick shape: " + brick.getBrickData().length + "x" +
                        (brick.getBrickData().length > 0 ? brick.getBrickData()[0].length : 0));

                int visibleCells = 0;
                int hiddenCells = 0;
                for (int r = 0; r < brick.getBrickData().length; r++) {
                    int boardY = brick.getyPosition() + r;
                    if (boardY >= GridRenderer.VISIBLE_START_ROW) {
                        visibleCells++;
                    } else {
                        hiddenCells++;
                    }
                }
                System.out.println("Visible cells: " + visibleCells + ", Hidden cells: " + hiddenCells);
                System.out.println("=== END DEBUG ===\n");
            }
        }
    }
}