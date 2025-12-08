package com.comp2042.logic.bricks;

import java.util.List;

/**
 * Interface representing a Tetris brick with its shape matrices for different rotations.
 */

public interface Brick {

    /**
     * Returns a list of 2D integer arrays representing the shape of the brick
     * in its various rotational states.
     *
     * @return List of 2D integer arrays for each rotation of the brick.
     */

    List<int[][]> getShapeMatrix();
}
