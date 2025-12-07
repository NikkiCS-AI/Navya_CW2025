package com.comp2042;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

/**
 * Interface for managing the game state, including pause status,
 * game over status, and cleared row count.
 */

public interface GameStateInterface {

    /** checks if the game is paused */
    boolean isPaused();

    /** sets the paused state of the game */
    void setPaused(boolean value);

    /** property representing the paused state of the game */
    BooleanProperty pausedProperty();

    /** checks if the game is over */
    boolean isGameOver();

    /** sets the game over state */
    void setGameOver(boolean value);

    /** property representing the game over state */
    BooleanProperty gameOverProperty();

    /** gets the number of cleared rows */
    int getClearedRowCount();

    /** property representing the number of cleared rows */
    IntegerProperty clearedRowCountProperty();

    /** adds to the cleared row count */
    void addClearedRows(int amount);

    /** resets the game state */
    void reset();
}
