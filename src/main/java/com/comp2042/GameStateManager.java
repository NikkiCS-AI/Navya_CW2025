package com.comp2042;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameStateManager implements GameStateInterface {
    private final BooleanProperty isPaused = new SimpleBooleanProperty(false);
    private final BooleanProperty isGameOver = new SimpleBooleanProperty(false);
    private final IntegerProperty clearedRowCount = new SimpleIntegerProperty(0);

    public boolean isPaused() {
        return isPaused.get();
    }

    public void setPaused(boolean value) {
        isPaused.set(value);
    }

    public BooleanProperty pausedProperty() {
        return isPaused;
    }

    public boolean isGameOver() {
        return isGameOver.get();
    }

    public void setGameOver(boolean value) {
        isGameOver.set(value);
    }

    public BooleanProperty gameOverProperty() {
        return isGameOver;
    }

    public int getClearedRowCount() {
        return clearedRowCount.get();
    }

    public IntegerProperty clearedRowCountProperty() {
        return clearedRowCount;
    }

    public void addClearedRows(int amount) {
        clearedRowCount.set(clearedRowCount.get() + amount);
    }

    public void resetClearedRows() {
        clearedRowCount.set(0);
    }

    public void reset() {
        isPaused.set(false);
        isGameOver.set(false);
        clearedRowCount.set(0);
    }
}