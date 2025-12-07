package com.comp2042;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Manages the high-level state of the game, including pause status,
 * game-over state, and the total number of cleared rows.
 *
 * <p>This class provides JavaFX property bindings, allowing UI components
 * to automatically update when the game state changes. It is typically used
 * as a lightweight state container for controllers and views.</p>
 *
 * <p>Supported tracked states:</p>
 * <ul>
 *     <li>{@code isPaused} – whether the game is currently paused</li>
 *     <li>{@code isGameOver} – whether the game has ended</li>
 *     <li>{@code clearedRowCount} – total number of rows cleared</li>
 * </ul>
 */

public class GameStateManager implements GameStateInterface {
    private final BooleanProperty isPaused = new SimpleBooleanProperty(false);
    private final BooleanProperty isGameOver = new SimpleBooleanProperty(false);
    private final IntegerProperty clearedRowCount = new SimpleIntegerProperty(0);

    /** Creates a new GameStateManager with default initial states:
     * not paused, not game over, and zero cleared rows.
     */
    public boolean isPaused() {
        return isPaused.get();
    }

    /** Sets the paused state of the game.
     *
     * @param value true to pause the game, false to resume
     */
    public void setPaused(boolean value) {
        isPaused.set(value);
    }

    /** Returns the paused state property for binding.
     *
     * @return BooleanProperty representing the paused state
     */
    public BooleanProperty pausedProperty() {
        return isPaused;
    }

    /** Returns whether the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return isGameOver.get();
    }

    /** Sets the game-over state.
     *
     * @param value true to mark the game as over, false otherwise
     */
    public void setGameOver(boolean value) {
        isGameOver.set(value);
    }

    /** Returns the game-over state property for binding.
     *
     * @return BooleanProperty representing the game-over state
     */
    public BooleanProperty gameOverProperty() {
        return isGameOver;
    }

    /** Returns the total number of cleared rows.
     *
     * @return the number of cleared rows
     */
    public int getClearedRowCount() {
        return clearedRowCount.get();
    }

    /** Returns the cleared row count property for binding.
     *
     * @return IntegerProperty representing the cleared row count
     */
    public IntegerProperty clearedRowCountProperty() {
        return clearedRowCount;
    }

    /** Increases the cleared row count by the specified amount.
     *
     * @param amount the number of rows to add to the cleared count
     */
    public void addClearedRows(int amount) {
        clearedRowCount.set(clearedRowCount.get() + amount);
    }

    /** Resets the cleared row count to zero. */
    public void resetClearedRows() {
        clearedRowCount.set(0);
    }

    /** Resets the game state to initial values:
     * not paused, not game over, and zero cleared rows.
     */
    public void reset() {
        isPaused.set(false);
        isGameOver.set(false);
        clearedRowCount.set(0);
    }
}