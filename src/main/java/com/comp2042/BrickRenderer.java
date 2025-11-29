package com.comp2042;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class BrickRenderer {

    private static final int PREVIEW_SIZE = 15;
    private static final int GAME_SIZE = 20;


    public static void updatePreviewPanel(GridPane previewPanel, ViewData nextBrick) {
        if (previewPanel == null || nextBrick == null) return;

        previewPanel.getChildren().clear();

        int[][] brickData = nextBrick.getBrickData();

        for (int r = 0; r < brickData.length; r++) {
            for (int c = 0; c < brickData[r].length; c++) {
                Rectangle rect = createBrickRectangle(PREVIEW_SIZE, brickData[r][c]);
                previewPanel.add(rect, c, r);
            }
        }
    }


    public void updateHoldPanel(GridPane holdPanel, int[][] holdShape) {
        if (holdPanel == null || holdShape == null) return;

        holdPanel.getChildren().clear();

        for (int r = 0; r < holdShape.length; r++) {
            for (int c = 0; c < holdShape[r].length; c++) {
                if (holdShape[r][c] != 0) {
                    Rectangle rect = createBrickRectangle(PREVIEW_SIZE, holdShape[r][c]);
                    holdPanel.add(rect, c, r);
                }
            }
        }
    }


    public void refreshBrick(ViewData brick,
                             Rectangle[][] brickRects,
                             GridPane brickPanel,
                             GridPane gamePanel,
                             boolean isPaused) {

        if (brick == null || brickRects == null || isPaused) return;


        positionBrickPanel(brickPanel, gamePanel, brick);


        int[][] brickData = brick.getBrickData();

        for (int r = 0; r < brickData.length; r++) {
            for (int c = 0; c < brickData[r].length; c++) {
                setRectColor(brickData[r][c], brickRects[r][c]);
            }
        }
    }

    public void refreshGameBackground(int[][] board, Rectangle[][] matrix) {
        if (board == null || matrix == null) return;

        for (int r = 2; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                setRectColor(board[r][c], matrix[r][c]);
            }
        }
    }


    private static Rectangle createBrickRectangle(int size, int colorValue) {
        Rectangle rect = new Rectangle(size, size);
        rect.setFill(BrickColour.getColour(colorValue));
        rect.getStyleClass().add("brick-common");
        rect.setArcHeight(6);
        rect.setArcWidth(6);
        return rect;
    }

    private static void setRectColor(int colorValue, Rectangle rectangle) {
        if (rectangle == null) return;
        rectangle.setFill(BrickColour.getColour(colorValue));
    }

    public void positionBrickPanel(GridPane brickPanel, GridPane gamePanel, ViewData brick) {
        brickPanel.setLayoutX(
                gamePanel.getLayoutX()
                        + brick.getxPosition() * (brickPanel.getVgap() + GAME_SIZE)
        );

        brickPanel.setLayoutY(
                -42 + gamePanel.getLayoutY()
                        + brick.getyPosition() * (brickPanel.getHgap() + GAME_SIZE)
        );
    }
}
