package com.comp2042;

import com.comp2042.logic.bricks.Brick;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiController implements Initializable {
    private static final int BRICK_SIZE = 20; // Size of each brick in pixels

    @FXML
    private Label clearedRows; // Label to show cleared rows

    @FXML
    private GridPane gamePanel; // The main game grid

    @FXML
    private Group groupNotification; // Group to hold notifications

    @FXML
    private GridPane brickPanel; // Panel to show the current brick

    @FXML
    private GameOverPanel gameOverPanel; // Panel to show game over message

    private Rectangle[][] displayMatrix; // Matrix to display the game state

    InputEventListener eventListener;

    private Rectangle[][] rectangles; // Rectangles for the current brick

    private Timeline timeLine; // Timeline for game loop

    final BooleanProperty isPause = new SimpleBooleanProperty(); // Property to track if the game is paused

    final BooleanProperty isGameOver = new SimpleBooleanProperty(); // Property to track if the game is over

   @FXML
   private Label bindScore;

    @FXML
    private Button pauseGame;

    @FXML
    private GridPane previewPanel;


    private AudioManager audioManager = new AudioManager();

    private int clearedRowCount = 0;

    @FXML
    private GridPane holdPanel;

    @FXML
    private Label holdLabel;

    private KeyInputHandler inputHandler;


    public void updateHoldDisplay(int[][] holdShape) {
        if (holdPanel == null) return;
        holdPanel.getChildren().clear();

        for (int i = 0; i < holdShape.length; i++) {
            for (int j = 0; j < holdShape[i].length; j++) {
                if (holdShape[i][j] != 0) {
                    Rectangle rect = new Rectangle(15, 15);
                    rect.setFill(BrickColour.getColour(holdShape[i][j]));
                    rect.setArcHeight(6);
                    rect.setArcWidth(6);
                    holdPanel.add(rect, j, i);
                }
            }
        }
    }



    @Override
    // initalises url and resources, prepares game interface and inputs from keyboard
    public void initialize(URL location, ResourceBundle resources) {
        Font.loadFont(getClass().getClassLoader().getResource("digital.ttf").toExternalForm(), 38);
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();// Request focus to capture key events
        inputHandler = new KeyInputHandler(this);

        // Set up key event handler
        gamePanel.setOnKeyPressed(keyEvent -> {
            KeyCode code = keyEvent.getCode();

            inputHandler.handleMovementKey(code);
            inputHandler.handleSystemKey(code);

            keyEvent.consume();
        });


        gameOverPanel.setVisible(false);

        final Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(0.9);
        reflection.setTopOffset(-12);

        Platform.runLater(() -> audioManager.playBackgroundMusic());

    }
//creates the tetris game board and the falling bricks
    public void initGameView(int[][] boardMatrix, ViewData brick) {
        displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = 2; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - 2);
            }
        }


        rectangles = new Rectangle[brick.getBrickData().length][brick.getBrickData()[0].length];
        for (int i = 0; i < brick.getBrickData().length; i++) {
            for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(BrickColour.getColour(brick.getBrickData()[i][j]));
                rectangles[i][j] = rectangle;
                brickPanel.add(rectangle, j, i);
            }
        }
        brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap() + brick.getxPosition() * BRICK_SIZE);
        brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap() + brick.getyPosition() * BRICK_SIZE);


        timeLine = new Timeline(new KeyFrame(
                Duration.millis(400),
                ae -> performMoveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
        ));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    public void updateNextBrick(ViewData nextBrick) {
        if (previewPanel == null) return;
        previewPanel.getChildren().clear();

        for (int i = 0; i < nextBrick.getBrickData().length; i++) {
            for (int j = 0; j < nextBrick.getBrickData()[i].length; j++) {
                Rectangle rect = new Rectangle(15, 15);
                rect.setFill(BrickColour.getColour(nextBrick.getBrickData()[i][j]));
                rect.setArcHeight(6);
                rect.setArcWidth(6);
                previewPanel.add(rect, j, i);
            }
        }
    }

// updates the brick's position on the game board
    public void refreshBrick(ViewData brick) {
        if (isPause.getValue() == Boolean.FALSE) {
            brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap() + brick.getxPosition() * BRICK_SIZE);
            brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap() + brick.getyPosition() * BRICK_SIZE);
            for (int i = 0; i < brick.getBrickData().length; i++) {
                for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                    setRectangleData(brick.getBrickData()[i][j], rectangles[i][j]);
                }
            }
        }
    }
// updates the part of the board that holds the stationary bricks
    public void refreshGameBackground(int[][] board) {
        for (int i = 2; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setRectangleData(board[i][j], displayMatrix[i][j]);
            }
        }
    }
// sets the color and shape of each brick on the board
    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(BrickColour.getColour(color));
        rectangle.setArcHeight(9);
        rectangle.setArcWidth(9);
    }

// moves bricks down, checks for cleared rows and updates score notifications
void performMoveDown(MoveEvent event) {
        if (isPause.getValue() == Boolean.FALSE) {
            DownData downData = eventListener.onDownEvent(event);
            if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
                NotificationPanel notificationPanel = new NotificationPanel("+" + downData.getClearRow().getScoreBonus());
                groupNotification.getChildren().add(notificationPanel);
                notificationPanel.showScore(groupNotification.getChildren());
                audioManager.playLineClearSound();
                clearedRows(downData.getClearRow().getLinesRemoved());
            }
            refreshBrick(downData.getViewData());
        }
        gamePanel.requestFocus();
    }

    //communicates with game controller
    public void setEventListener(InputEventListener eventListener) {
        this.eventListener = eventListener;
    }

    // binds a score property to the game controller's score so it auto updates on the screen
    public void bindScore(IntegerProperty scoreProperty) {
        if (bindScore != null) {
            bindScore.textProperty().bind(scoreProperty.asString("Score: %d"));

        }
    }

    //stops timeline and shows game over panel
    public void gameOver() {
        timeLine.stop();
        gameOverPanel.setVisible(true);
        isGameOver.setValue(Boolean.TRUE);
        audioManager.stopBackgroundMusic();
        audioManager.playGameOverSound();
    }

    //resets the games and starts again
    public void newGame(ActionEvent actionEvent) {
        timeLine.stop();
        gameOverPanel.setVisible(false);
        eventListener.createNewGame();
        gamePanel.requestFocus();
        timeLine.play();
        isPause.setValue(Boolean.FALSE);
        isGameOver.setValue(Boolean.FALSE);
        Platform.runLater(() -> audioManager.playBackgroundMusic());
        clearedRowCount = 0;
        clearedRows.setText("Cleared Rows: " + clearedRowCount);
    }

    //stops the timeline to pause the game
    public void pauseGame(ActionEvent actionEvent) {
        if (isPause.getValue() == Boolean.FALSE) {
            timeLine.stop();
            isPause.setValue(Boolean.TRUE);
            audioManager.stopBackgroundMusic();
            pauseGame.setText("Resume");
        } else {
            timeLine.play();
            isPause.setValue(Boolean.FALSE);
            audioManager.playBackgroundMusic();
            pauseGame.setText("Pause");
        }
        gamePanel.requestFocus();
    }

    public void clearedRows(int linesCleared) {
        clearedRowCount += linesCleared;
        Platform.runLater(() -> clearedRows.setText("Cleared Rows: " + clearedRowCount));
        }
}




