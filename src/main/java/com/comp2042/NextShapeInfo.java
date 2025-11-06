package com.comp2042;

public final class NextShapeInfo {

    private final int[][] shape;
    private final int position;

    //creates a new NextShapeInfo object
    public NextShapeInfo(final int[][] shape, final int position) {
        this.shape = shape;
        this.position = position;
    }

    //return a copy of the shape to prevent external modification
    public int[][] getShape() {
        return MatrixOperations.copy(shape);
    }

    //return the position of the shape
    public int getPosition() {
        return position;
    }
}
