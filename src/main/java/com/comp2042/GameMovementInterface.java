package com.comp2042;

/**
 * Interface for handling game movement actions.
 */

public interface GameMovementInterface {
    /** moves the current piece down by one unit */
    void softDrop();

    /** moves the current piece left by one unit */
    void moveLeft();

    /** moves the current piece right by one unit */
    void moveRight();

    /** rotates the current piece */
    void rotate();

    /** holds the brick in hold panel */
    void hold();

    /** starts a new game */
    void newGame();
}