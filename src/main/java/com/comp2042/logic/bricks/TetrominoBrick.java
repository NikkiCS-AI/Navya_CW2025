package com.comp2042.logic.bricks;
import java.util.List;

/**
 * Represents a Tetromino brick with a specific shape type.
 */

public class TetrominoBrick implements Brick {
    private final TetrominoShapeType type;

    /**
     * Constructs a TetrominoBrick with the given shape type.
     *
     * @param type The Tetromino shape type.
     */
    public TetrominoBrick(TetrominoShapeType type) {
        this.type = type;
    }

    @Override
    /**
     * Returns the shape matrix of the Tetromino brick.
     *
     * @return A list of 2D integer arrays representing the shape matrix.
     */
    public List<int[][]> getShapeMatrix() {
        return type.getRotations();
    }
}
