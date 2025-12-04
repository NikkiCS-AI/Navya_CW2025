package com.comp2042;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;

public class GridRenderer {
    public static final int BRICK_SIZE = 20;
    public static final int VISIBLE_ROWS = 20; // Changed from 22 to 20
    public static final int VISIBLE_START_ROW = 4; // Changed from 2 to 4

    public void createGrid(int[][] boardMatrix, ViewData brick, GuiController controller) {
        // Create display matrix for visible rows only
        controller.displayMatrix = new Rectangle[VISIBLE_ROWS][10];

        System.out.println("=== GridRenderer.createGrid ===");
        System.out.println("Creating " + VISIBLE_ROWS + " visible rows");
        System.out.println("Visible rows: " + VISIBLE_START_ROW + " to " + (VISIBLE_START_ROW + VISIBLE_ROWS - 1));

        for (int i = 0; i < VISIBLE_ROWS; i++) {  // Create visible rows
            int boardRow = i + VISIBLE_START_ROW; // Convert to board row

            for (int j = 0; j < 10; j++) {
                Rectangle rect = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rect.setFill(Color.TRANSPARENT); // Start transparent
                rect.getStyleClass().add("brick-common");
                rect.setStroke(Color.rgb(60, 60, 60, 0.3));
                rect.setStrokeWidth(0.5);

                controller.displayMatrix[i][j] = rect;
                controller.gamePanel.add(rect, j, i);

                // Debug: Show grid coordinates
                if (i == 0 && j == 0) {
                    System.out.println("Grid[0,0] -> Board[" + j + "," + boardRow + "]");
                }
            }
        }

        // Initialize brick rectangles
        if (brick != null) {
            int[][] brickData = brick.getBrickData();
            controller.brickRectangles = new Rectangle[brickData.length][brickData[0].length];

            for (int i = 0; i < brickData.length; i++) {
                for (int j = 0; j < brickData[i].length; j++) {
                    controller.brickRectangles[i][j] = null;
                }
            }
        }

        System.out.println("Grid created: " + VISIBLE_ROWS + "x10");
        System.out.println("=== END GridRenderer ===");
    }
}