package com.comp2042;

import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * GuiController is responsible for managing the GUI components of the Tetris game.
 * It handles game initialization, user input, game state updates, and rendering.
 */

public class GuiController implements Initializable, GameMovementInterface {
    static final int BRICK_SIZE = 20;

    private final GridRenderer gridRenderer;
    private final BrickRenderer brickRenderer;
    private final GameLoopInterface gameLoop;
    private final GameStateInterface gameState;
    private final AudioManagerInterface audioManager;

    @FXML private PauseMenu pauseMenu;

    private RowsClearedEffectsHandler rowsEffectsHandler;
    private GameOverHandler gameOverHandler;

    @FXML private HBox gameRoot;
    @FXML private HBox topBar;

    @FXML private Label clearedRows;
    @FXML private Label bindScore;

    @FXML GridPane gamePanel;
    @FXML private GridPane brickPanel;
    @FXML private GridPane previewPanel;
    @FXML private GridPane holdPanel;

    @FXML private Group groupNotification;
    @FXML private GameOverPanel gameOverPanel;
    @FXML private Button pauseGame;

   @FXML private StartMenu startMenuScreen;
   @FXML private BorderPane gameScreen;

    InputEventListener eventListener;
    private KeyInputHandler inputHandler;

    Rectangle[][] displayMatrix;
    Rectangle[][] brickRectangles;

    private GameController gameController;

    private GameOverUIBuilder gameOverUIBuilder;

    /** Constructor for GuiController */
    public GuiController() {
        this.gridRenderer = new GridRenderer();
        this.brickRenderer = new BrickRenderer();
        this.gameLoop = new GameLoopManager();
        this.gameState = new GameStateManager();
        this.audioManager = new AudioManager();
    }

    private StartMenu.Difficulty currentDifficulty;

    @Override
    /** Initialize the GUI controller */
    public void initialize(URL location, ResourceBundle resources) {
        startMenuScreen.setOnStartGame(this::startGame);
        startMenuScreen.setOnQuitGame(this::quitGame);

        startMenuScreen.setVisible(true);
        startMenuScreen.setManaged(true);
        gameScreen.setVisible(false);
        gameScreen.setManaged(false);

        // In initialize() method
        pauseMenu.getResumeButton().setOnAction(e -> resumeGame());
        pauseMenu.getNewGameButton().setOnAction(e -> restartGame());
        pauseMenu.getQuitButton().setOnAction(e -> quitToMenu());

        loadFont();
        setupHelpers();
        createGamePanel();
        setupInputHandler();
        setupReflection();

        gameOverPanel.setVisible(false);
        gameOverPanel.setManaged(false);
        gameOverUIBuilder = new GameOverUIBuilder(gameOverPanel, this::restartGame, this::quitToMenu);
    }

    /** Handle game over state */
    public void gameOver() {
        System.out.println("=== GAME OVER CALLED ===");

        // Update score in game over panel
        if (bindScore != null) {
            String scoreText = bindScore.getText();
            System.out.println("Final score: " + scoreText);

            // Extract just the number if it's in "Score: X" format
            if (scoreText.contains(":")) {
                String scoreValue = scoreText.split(":")[1].trim();
                gameOverPanel.setScore(scoreValue);
            } else {
                gameOverPanel.setScore(scoreText);
            }
        }

        // Show game over panel using GameOverHandler
        gameOverHandler.showGameOver();

        // Add buttons for user interaction
        gameOverUIBuilder.addGameOverButtons();

        // Make sure panel is on top
        gameOverPanel.toFront();

        System.out.println("Game Over displayed with options.");
    }

    /** Resume the game from pause */
    private void resumeGame() {
        System.out.println("Resuming game from pause menu...");
        pauseMenu.hideMenu();

        if (gameState.isPaused()) {
            gameLoop.resume();
            gameState.setPaused(false);
            audioManager.playBackgroundMusic();
            pauseGame.setText("Pause");
        }
        gamePanel.requestFocus();
    }

    /** Restart the game */
    private void restartGame() {
        System.out.println("Starting new game...");
        pauseMenu.hideMenu();

        if (gameOverPanel.isVisible()) {
            gameOverPanel.setVisible(false);
            gameOverPanel.setManaged(false);
            gameOverPanel.setBottom(null); // Remove buttons
        }

        // Reset game state
        gameOverHandler.resetForNewGame();
        clearedRows.setText("0");

        clearGamePanels();

        gameController = new GameController(this);

        double speedMultiplier = GameLoopManager.getSpeedForDifficulty(currentDifficulty);
        gameLoop.setSpeed(speedMultiplier);

        // Create new game
        if (eventListener != null) {
            eventListener.createNewGame();
        }

        audioManager.playBackgroundMusic();
        gameLoop.start(() -> performMoveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD)));
        gamePanel.requestFocus();

        System.out.println("New game started successfully");
    }

    /** Quit to main menu */
    private void quitToMenu() {
        System.out.println("\n=== QUIT TO MENU ===");

        // 1. Hide pause menu
        pauseMenu.hideMenu();

        // 2. Stop all game processes
        gameLoop.stop();
        audioManager.stopBackgroundMusic();

        // 3. Reset game state
        gameState.setPaused(false);
        gameState.setGameOver(false);

        // 4. NULLIFY the game controller to force fresh creation
        gameController = null;
        eventListener = null;

        // 5. Clear visual elements
        clearGamePanels();

        // 6. Switch screens
        gameScreen.setVisible(false);
        gameScreen.setManaged(false);
        startMenuScreen.setVisible(true);
        startMenuScreen.setManaged(true);

        System.out.println("Game fully reset and controller nullified\n");
    }

    /** Start a fresh game */
    private void startGame() {
        System.out.println("\n=== STARTING FRESH GAME ===");

        // 1. Ensure controller is null (fresh start)
        gameController = null;
        eventListener = null;

        // 2. Get difficulty
        currentDifficulty = startMenuScreen.getSelectedDifficulty();

        // 3. Set speed
        double speedMultiplier = GameLoopManager.getSpeedForDifficulty(currentDifficulty);
        gameLoop.setSpeed(speedMultiplier);

        // 4. Switch screens
        startMenuScreen.setVisible(false);
        startMenuScreen.setManaged(false);
        gameScreen.setVisible(true);
        gameScreen.setManaged(true);

        // 5. Clear panels (just in case)
        clearGamePanels();

        // 6. Initialize FRESH game
        initGame();
        System.out.println("Fresh game initialized\n");
    }

    /** Quit the game application */
    private void quitGame() {
        System.out.println("Quitting game from StartMenu...");

        // Stop any running game loops or music
        if (gameLoop != null) {
            gameLoop.stop();
        }

        if (audioManager != null) {
            audioManager.stopBackgroundMusic();
        }

        // Get the window and close it
        Stage stage = (Stage) startMenuScreen.getScene().getWindow();
        stage.close();
    }

    /** Clear game panels and reset state */
    void clearGamePanels() {
        // Clear the game panel
        gamePanel.getChildren().clear();
        brickPanel.getChildren().clear();
        previewPanel.getChildren().clear();
        holdPanel.getChildren().clear();

        // Reset display matrices
        displayMatrix = null;
        brickRectangles = null;

        // Reset UI labels
        if (bindScore != null) {
            bindScore.textProperty().unbind();
            bindScore.setText("0");
        }

        if (clearedRows != null) {
            clearedRows.setText("0");
        }
    }

    /** Set up helper classes */
    private void setupHelpers() {
        rowsEffectsHandler = new RowsClearedEffectsHandler(audioManager, gameState, clearedRows);
        gameOverHandler = new GameOverHandler(audioManager, gameLoop, gameState, gameOverPanel);
    }

    /** Set up input handler for key events */
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

    /** Set up reflection effect for UI */
    private void setupReflection() {
        Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(0.9);
        reflection.setTopOffset(-12);
    }

    /** Create and configure the game panel */
    private void createGamePanel() {
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();

        // Set grid lines for debugging
        gamePanel.setGridLinesVisible(false); // Set to true for debugging
    }

    /** Load custom font for the game */
    private void loadFont() {
        try {
            Font.loadFont(getClass().getClassLoader().getResource("digital.ttf").toExternalForm(), 38);
        } catch (Exception e) {
            System.err.println("Could not load font: " + e.getMessage());
        }
    }

    public boolean isPaused() { return gameState.isPaused(); }
    public boolean isGameOver() { return gameState.isGameOver(); }

    /** Initialize the game */
    public void initGame() {
        gameController = new GameController(this);
        audioManager.playBackgroundMusic();

        // Debug info
        System.out.println("\n=== GuiController.initGame ===");
        System.out.println("Game panel size: " + gamePanel.getPrefWidth() + "x" + gamePanel.getPrefHeight());
        System.out.println("Visible rows: " + GridRenderer.VISIBLE_ROWS);
        System.out.println("Visible start row: " + GridRenderer.VISIBLE_START_ROW);
    }

    /** Initialize the game view with the board matrix and current brick */
    public void initGameView(int[][] boardMatrix, ViewData brick) {
        System.out.println("\n=== initGameView ===");
        System.out.println("Board matrix: " + boardMatrix.length + "x" + boardMatrix[0].length);

        gridRenderer.createGrid(boardMatrix, brick, this);

        gameLoop.start(() -> performMoveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD)));
    }

    /** Create the game grid */
    public void updateNextBrick(ViewData nextBrick) {
        BrickRenderer.updatePreviewPanel(previewPanel, nextBrick);
    }

    /** Refresh the brick display */
    public void refreshBrick(ViewData brick) {
        brickRenderer.refreshBrick(brick, brickRectangles, brickPanel, gamePanel, gameState.isPaused());
    }

    /** Refresh the game background */
    public void refreshGameBackground(int[][] board) {
        brickRenderer.refreshGameBackground(board, displayMatrix);
    }

    public void updateHoldDisplay(int[][] holdShape) {
        brickRenderer.updateHoldPanel(holdPanel, holdShape);
    }

    /** Perform move down action */
    public void performMoveDown(MoveEvent event) {
        if (gameState.isPaused()) {
            gamePanel.requestFocus();
            return;
        }

        DownData data = eventListener.onDownEvent(event);

        rowsEffectsHandler.apply(data.getClearRow());

        refreshBrick(data.getViewData());

        gamePanel.requestFocus();
    }

    /** Bind score property to the score label */
    public void bindScore(IntegerProperty scoreProperty) {
        if (bindScore != null) {
            bindScore.textProperty().unbind();
            bindScore.textProperty().bind(scoreProperty.asString("%d"));
        }
    }

    /** Pause the game */
    public void pauseGame(javafx.event.ActionEvent actionEvent) {
        if (!gameState.isPaused()) {
            gameLoop.pause();
            gameState.setPaused(true);
            audioManager.stopBackgroundMusic();
            pauseGame.setText("Pause");

            // Show pause menu
            pauseMenu.showMenu();
            System.out.println("Game paused - showing pause menu");
        } else {
            resumeGame();
        }
        gamePanel.requestFocus();
    }

    /** Get the brick panel */
    public GridPane getbrickPanel() { return brickPanel; }

    /** Set the input event listener */
    public void setEventListener(InputEventListener listener) {
        this.eventListener = listener;
    }

    @Override
    /** Perform soft drop action */
    public void softDrop() {
        performMoveDown(new MoveEvent(EventType.DOWN, EventSource.USER));
    }

    @Override
    /** Move the brick left */
    public void moveLeft() {
        refreshBrick(eventListener.onLeftEvent(new MoveEvent(EventType.LEFT, EventSource.USER)));
    }

    @Override
    /** Move the brick right */
    public void moveRight() {
        refreshBrick(eventListener.onRightEvent(new MoveEvent(EventType.RIGHT, EventSource.USER)));
    }

    @Override
    /** Rotate the brick */
    public void rotate() {
        refreshBrick(eventListener.onRotateEvent(new MoveEvent(EventType.ROTATE, EventSource.USER)));
    }

    @Override
    /** Perform hard drop action */
    public void hold() {
        eventListener.onHoldEvent();
    }

    @Override
    /** Start a new game */
    public void newGame() {
        restartGame();
    }

    @Override
    /** Perform hard drop action */
    public void hardDrop() {
        System.out.println("Hard drop triggered from GUI");

        if (gameState.isPaused() || gameState.isGameOver()) {
            System.out.println("Game is paused or over - ignoring hard drop");
            return;
        }

        // Call hardDrop on the game controller
        if (eventListener != null) {
            eventListener.onHardDropEvent();
        } else {
            System.out.println("eventListener is null");
        }

        // Request focus back to game panel
        gamePanel.requestFocus();
    }
}