package com.comp2042;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;

public class GridRenderer {

    public static final int BRICK_SIZE = 20;
    public static final int BRICK_Y_OFFSET = -42;


    public void createGrid(int[][] boardMatrix, ViewData brick, GuiController controller) {

        controller.displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];

        for (int i = 2; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {

                Rectangle rect = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rect.setFill(Color.TRANSPARENT);
                rect.getStyleClass().add("brick-common");

                controller.displayMatrix[i][j] = rect;
                controller.gamePanel.add(rect, j, i - 2);
            }
        }

        int[][] brickData = brick.getBrickData();
        controller.brickRectangles = new Rectangle[brickData.length][brickData[0].length];

        GridPane brickPanel = controller.getbrickPanel();

        for (int i = 0; i < brickData.length; i++) {
            for (int j = 0; j < brickData[i].length; j++) {

                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(BrickColour.getColour(brickData[i][j]));
                rectangle.getStyleClass().add("brick-common");

                controller.brickRectangles[i][j] = rectangle;
                brickPanel.add(rectangle, j, i);
            }
        }
    }

}
