package com.comp2042;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


class RowsClearedEffectsHandlerTest {
    static class TestAudioManager implements AudioManagerInterface {
        boolean soundIsPlayed = false;

        @Override
        public void playBackgroundMusic() {

        }

        @Override
        public void stopBackgroundMusic() {

        }

        @Override public void playLineClearSound() {
            soundIsPlayed = true;
        }

        @Override
        public void playGameOverSound() {

        }
    }

    static class TestGameState implements GameStateInterface {
        int rowsClearedCount = 0;

        @Override public void addClearedRows(int count) {
            rowsClearedCount += count;
        }

        @Override
        public void reset() {

        }

        @Override
        public boolean isPaused() {
            return false;
        }

        @Override
        public void setPaused(boolean value) {

        }

        @Override
        public BooleanProperty pausedProperty() {
            return null;
        }

        @Override
        public boolean isGameOver() {
            return false;
        }

        @Override
        public void setGameOver(boolean value) {

        }

        @Override
        public BooleanProperty gameOverProperty() {
            return null;
        }

        @Override public int getClearedRowCount() {
            return rowsClearedCount;
        }

        @Override
        public IntegerProperty clearedRowCountProperty() {
            return null;
        }
    }



    @Test
    void testHandleRowsClearedEmptyGrid() {
        TestAudioManager audioManager = new TestAudioManager();
        TestGameState gameState = new TestGameState();
        RowsClearedEffectsHandler handler = new RowsClearedEffectsHandler(audioManager, gameState, null);

        int[][] dummyGrid = new int[20][10];
        ClearRow clearedRow = new ClearRow(0, dummyGrid, 0);

        handler.apply(clearedRow);

        assertFalse(audioManager.soundIsPlayed, "No sound should be played for empty grid");
        assertEquals(0, gameState.getClearedRowCount(), "No rows should be added for empty grid");
    }

    @Test
    void testHandleRowsClearedEmptyRows() {
        TestAudioManager audioManager = new TestAudioManager();
        TestGameState gameState = new TestGameState();
        RowsClearedEffectsHandler handler = new RowsClearedEffectsHandler(audioManager, gameState, null);

        handler.apply(null);

        assertFalse(audioManager.soundIsPlayed, "No sound should be played for zero cleared rows");
        assertEquals(0, gameState.getClearedRowCount(), "No rows should be added for zero cleared rows");
    }

}