package com.comp2042;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

    public class BrickRenderer {
        private static final int PREVIEW_BRICK_SIZE = 15;
        private static final int GAME_BRICK_SIZE = 20;

        public static void updatePreviewPanel(GridPane previewPanel, ViewData nextBrick) {
            if (previewPanel == null) return;
            previewPanel.getChildren().clear();

            int[][] brickData = nextBrick.getBrickData();
            for (int i = 0; i < brickData.length; i++) {
                for (int j = 0; j < brickData[i].length; j++) {
                    Rectangle rect = createBrickRectangle(PREVIEW_BRICK_SIZE, brickData[i][j]);
                    previewPanel.add(rect, j, i);
                }
            }
        }

        public void updateHoldPanel(GridPane holdPanel, int[][] holdShape) {
            if (holdPanel == null) return;
            holdPanel.getChildren().clear();

            for (int i = 0; i < holdShape.length; i++) {
                for (int j = 0; j < holdShape[i].length; j++) {
                    if (holdShape[i][j] != 0) {
                        Rectangle rect = createBrickRectangle(PREVIEW_BRICK_SIZE, holdShape[i][j]);
                        holdPanel.add(rect, j, i);
                    }
                }
            }
        }

        public void refreshBrick(ViewData brick, Rectangle[][] rectangles, GridPane brickPanel,
                                 GridPane gamePanel, boolean isPaused) {
            if (isPaused) return;

            // Update position
            brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap()
                    + brick.getxPosition() * GAME_BRICK_SIZE);
            brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap()
                    + brick.getyPosition() * GAME_BRICK_SIZE);

            // Update colors
            int[][] brickData = brick.getBrickData();
            for (int i = 0; i < brickData.length; i++) {
                for (int j = 0; j < brickData[i].length; j++) {
                    setRectangleData(brickData[i][j], rectangles[i][j]);
                }
            }
        }

        public void refreshGameBackground(int[][] board, Rectangle[][] displayMatrix) {
            for (int i = 2; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    setRectangleData(board[i][j], displayMatrix[i][j]);
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

        private void setRectangleData(int color, Rectangle rectangle) {
            rectangle.setFill(BrickColour.getColour(color));
            rectangle.setArcHeight(9);
            rectangle.setArcWidth(9);
        }
    }

