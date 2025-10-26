package com.comp2042;

public final class ViewData {

    private final int[][] brickData;
    private final int xPosition;
    private final int yPosition;
    private final int[][] nextBrickData;

    public ViewData(int[][] brickData, int xPosition, int yPosition, int[][] nextBrickData) {
        if (brickData == null) {
            throw new IllegalArgumentException("brickData cannot be null");
        }
        if (xPosition < 0) {
            throw new IllegalArgumentException("xPosition cannot be negative");
        }
        if (yPosition < 0) {
            throw new IllegalArgumentException("yPosition cannot be negative");
        }
        if (nextBrickData == null) {
            throw new IllegalArgumentException("nextBrickData cannot be null");
        }
        this.brickData = brickData;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.nextBrickData = nextBrickData;
    }

    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int[][] getNextBrickData() {
        return MatrixOperations.copy(nextBrickData);
    }
}
