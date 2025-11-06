package com.comp2042;

public final class ViewData {

    private final int[][] brickData;
    private final int xPosition;
    private final int yPosition;
    private final int[][] nextBrickData;

    // creates and initialises view data object with brick data, position and next brick data
    public ViewData(int[][] brickData, int xPosition, int yPosition, int[][] nextBrickData) {
        this.brickData = brickData;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.nextBrickData = nextBrickData;
    }

    // returns brick shape data
    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }

    // returns x position of brick
    public int getxPosition() {
        return xPosition;
    }

    // returns y position of brick
    public int getyPosition() {
        return yPosition;
    }

    // returns next brick shape data
    public int[][] getNextBrickData() {
        return MatrixOperations.copy(nextBrickData);
    }
}
