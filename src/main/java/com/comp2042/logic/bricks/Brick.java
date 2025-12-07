package com.comp2042.logic.bricks;

import java.util.List;

/**
 * Interface representing a Tetris brick with its shape matrices for different rotations.
 */

public interface Brick {

    List<int[][]> getShapeMatrix();
}
