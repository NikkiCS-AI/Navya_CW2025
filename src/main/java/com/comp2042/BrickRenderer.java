package com.comp2042;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class BrickRenderer {
    private static final int PREVIEW_SIZE = 15;
    private static final int GAME_SIZE = 20;

    // Use the same constants as GridRenderer
    private static final int VISIBLE_ROWS = GridRenderer.VISIBLE_ROWS;
    private static final int VISIBLE_START_ROW = GridRenderer.VISIBLE_START_ROW;

    public void refreshBrick(ViewData brick,
                             Rectangle[][] brickRects,
                             GridPane brickPanel,
                             GridPane gamePanel,
                             boolean isPaused) {

        if (brick == null || brickRects == null || isPaused) return;

        // Clear old brick from gamePanel
        clearOldBrick(gamePanel, brickRects);

        // Add new brick to gamePanel at correct positions
        addBrickToGamePanel(brick, brickRects, gamePanel);

        // Hide the separate brick panel
        brickPanel.setVisible(false);
    }

    private void clearOldBrick(GridPane gamePanel, Rectangle[][] brickRects) {
        if (brickRects == null) return;

        for (int i = 0; i < brickRects.length; i++) {
            if (brickRects[i] == null) continue;

            for (int j = 0; j < brickRects[i].length; j++) {
                Rectangle rect = brickRects[i][j];
                if (rect != null && gamePanel.getChildren().contains(rect)) {
                    gamePanel.getChildren().remove(rect);
                    brickRects[i][j] = null; // Clear the reference
                }
            }
        }
    }

    private void addBrickToGamePanel(ViewData brick, Rectangle[][] brickRects, GridPane gamePanel) {
        int[][] brickData = brick.getBrickData();
        int startX = brick.getxPosition();
        int startY = brick.getyPosition();

        System.out.println("BrickRenderer: Brick at (" + startX + "," + startY + ")");

        for (int r = 0; r < brickData.length; r++) {
            for (int c = 0; c < brickData[r].length; c++) {
                if (brickData[r][c] != 0) {
                    int boardY = startY + r;
                    int boardX = startX + c;

                    // Check if this cell should be visible
                    if (boardX >= 0 && boardX < 10 &&
                            boardY >= VISIBLE_START_ROW && boardY < VISIBLE_START_ROW + VISIBLE_ROWS) {

                        // Convert to visible grid coordinates
                        int visibleGridY = boardY - VISIBLE_START_ROW;

                        // Create new rectangle
                        Rectangle rect = new Rectangle(GAME_SIZE, GAME_SIZE);
                        rect.setFill(BrickColour.getColour(brickData[r][c]));
                        rect.getStyleClass().add("brick-common");
                        rect.setArcHeight(4);
                        rect.setArcWidth(4);

                        // Store in array
                        brickRects[r][c] = rect;

                        // Add to game panel
                        gamePanel.add(rect, boardX, visibleGridY);

                        System.out.println("  Adding brick cell at grid [" + boardX + "," + visibleGridY + "]");
                    }
                }
            }
        }
    }

    public void refreshGameBackground(int[][] board, Rectangle[][] matrix) {
        if (board == null || matrix == null) return;

        System.out.println("=== refreshGameBackground ===");
        System.out.println("Board has " + board.length + " rows total");
        System.out.println("Matrix has " + matrix.length + " visible rows");

        for (int visibleGridY = 0; visibleGridY < VISIBLE_ROWS; visibleGridY++) {
            int boardRow = visibleGridY + VISIBLE_START_ROW;

            if (boardRow >= board.length) {
                System.out.println("ERROR: boardRow " + boardRow + " >= board.length " + board.length);
                continue;
            }

            for (int col = 0; col < 10; col++) {
                if (visibleGridY < matrix.length && col < matrix[visibleGridY].length) {
                    if (col < board[boardRow].length) {
                        int colorValue = board[boardRow][col];
                        setRectColor(colorValue, matrix[visibleGridY][col]);
                    }
                }
            }
        }
    }

    public static void updatePreviewPanel(GridPane previewPanel, ViewData nextBrick) {
        if (previewPanel == null || nextBrick == null) return;

        previewPanel.getChildren().clear();

        int[][] brickData = nextBrick.getBrickData();

        for (int r = 0; r < brickData.length; r++) {
            for (int c = 0; c < brickData[r].length; c++) {
                if (brickData[r][c] != 0) {
                    Rectangle rect = createBrickRectangle(PREVIEW_SIZE, brickData[r][c]);
                    previewPanel.add(rect, c, r);
                }
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

        if (colorValue == 0) {
            rectangle.setFill(javafx.scene.paint.Color.TRANSPARENT);
            rectangle.setStroke(javafx.scene.paint.Color.rgb(60, 60, 60, 0.3));
        } else {
            rectangle.setFill(BrickColour.getColour(colorValue));
            rectangle.setStroke(javafx.scene.paint.Color.TRANSPARENT);
        }
    }
}