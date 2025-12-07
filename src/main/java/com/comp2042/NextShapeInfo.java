package com.comp2042;

/**
 * The NextShapeInfo class encapsulates information about the next Tetris shape
 * to be displayed in the preview area. It holds the shape's matrix representation
 * and its rotation position.
 */

public final class NextShapeInfo {

    private final int[][] shape;
    private final int position;

    /** creates a new NextShapeInfo object */
    public NextShapeInfo(final int[][] shape, final int position) {
        this.shape = shape;
        this.position = position;
    }

    /** return a copy of the shape to prevent external modification */
    public int[][] getShape() {
        return MatrixOperations.copy(shape);
    }

    /** return the position of the shape */
    public int getPosition() {
        return position;
    }
}
