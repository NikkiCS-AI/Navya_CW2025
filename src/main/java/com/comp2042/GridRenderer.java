package com.comp2042;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;



public class GridRenderer {
    void createGrid(int[][] boardMatrix, ViewData brick, GuiController controller) {
        controller.displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = 2; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(GuiController.BRICK_SIZE, GuiController.BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.getStyleClass().add("brick-common");
                controller.displayMatrix[i][j] = rectangle;
                controller.gamePanel.add(rectangle, j, i - 2);
            }
        }

        int[][] brickData = brick.getBrickData();
        controller.rectangles = new Rectangle[brickData.length][brickData[0].length];
        GridPane brickPanel = controller.getbrickPanel();
        for (int i = 0; i < brickData.length; i++) {
            for (int j = 0; j < brickData[i].length; j++) {
                Rectangle rectangle = new Rectangle(GuiController.BRICK_SIZE, GuiController.BRICK_SIZE);
                rectangle.setFill(BrickColour.getColour(brickData[i][j]));
                rectangle.getStyleClass().add("brick-common");
                controller.rectangles[i][j] = rectangle;
                brickPanel.add(rectangle, j, i);
            }
        }


    }
}