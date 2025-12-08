package com.comp2042;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;

/**
 * Responsible for creating and rendering the visible grid portion of the Tetris board.
 * Adjusted to show only 20 visible rows starting from row index 4.
 */

/**
 * Responsible for creating and rendering the visible grid portion of the Tetris board.
 * Adjusted to show only 20 visible rows starting from row index 4.
 */
public class GridRenderer {
    public static final int BRICK_SIZE = 20; /** Size of each brick in pixels */
    public static final int VISIBLE_ROWS = 20; /** Number of visible rows on the game panel */
    public static final int VISIBLE_START_ROW = 4; /** Starting row index for visible grid */

    /**
     * Creates the visible grid on the game panel.
     *
     * @param boardMatrix The full game board matrix.
     * @param brick       The current brick data.
     * @param controller  The GUI controller managing the game UI.
     */
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