package com.comp2042;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

public interface GameStateInterface {
    boolean isPaused();
    void setPaused(boolean value);
    BooleanProperty pausedProperty();

    boolean isGameOver();
    void setGameOver(boolean value);
    BooleanProperty gameOverProperty();

    int getClearedRowCount();
    IntegerProperty clearedRowCountProperty();
    void addClearedRows(int amount);
    void reset();
}
