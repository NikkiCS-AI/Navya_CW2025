package com.comp2042.logic.bricks;

/**
 * Interface for generating Tetris bricks.
 */

public interface BrickGenerator {

    /**
     * Gets the current brick.
     *
     * @return the current Brick
     */
    Brick getBrick();

    /**
     * Gets the next brick.
     *
     * @return the next Brick
     */
    Brick getNextBrick();
}
