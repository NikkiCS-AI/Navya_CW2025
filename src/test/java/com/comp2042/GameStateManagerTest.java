package com.comp2042;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import static org.junit.jupiter.api.Assertions.*;


class GameStateManagerTest {
    private GameStateManager gameState;
    @BeforeEach
    void setUp() {
        gameState = new GameStateManager();
    }
    @Test
    void isPaused() {
        assertFalse(gameState.isPaused());

        gameState.setPaused(true);
        assertTrue(gameState.isPaused());

        gameState.setPaused(false);
        assertFalse(gameState.isPaused());
    }
    @Test
    void setPaused() {
        gameState.setPaused(true);
        assertTrue(gameState.isPaused());

        gameState.setPaused(false);
        assertFalse(gameState.isPaused());
    }
    @Test
    void pausedProperty() {
        BooleanProperty pausedProperty = gameState.pausedProperty();
        assertNotNull(pausedProperty);

        assertFalse(pausedProperty.get());
        gameState.setPaused(true);
        assertTrue(pausedProperty.get());
    }
    @Test
    void isGameOver() {
        assertFalse(gameState.isGameOver());

        gameState.setGameOver(true);
        assertTrue(gameState.isGameOver());

        gameState.setGameOver(false);
        assertFalse(gameState.isGameOver());
    }

    @Test
    void setGameOver() {
        gameState.setGameOver(true);
        assertTrue(gameState.isGameOver());

        gameState.setGameOver(false);
        assertFalse(gameState.isGameOver());
    }

    @Test
    void gameOverProperty() {
        BooleanProperty gameOverProperty = gameState.gameOverProperty();
        assertNotNull(gameOverProperty);

        assertFalse(gameOverProperty.get());
        gameState.setGameOver(true);
        assertTrue(gameOverProperty.get());
    }

    @Test
    void getClearedRowCount() {
        assertEquals(0, gameState.getClearedRowCount());
        gameState.addClearedRows(5);
        assertEquals(5, gameState.getClearedRowCount());

        gameState.addClearedRows(3);
        assertEquals(8, gameState.getClearedRowCount());
    }
    @Test
    void clearedRowCountProperty() {
        IntegerProperty clearedRowCountProperty = gameState.clearedRowCountProperty();
        assertNotNull(clearedRowCountProperty);

        assertEquals(0, clearedRowCountProperty.get());
        gameState.addClearedRows(4);
        assertEquals(4, clearedRowCountProperty.get());
    }
    @Test
    void addClearedRows() {
        gameState.addClearedRows(5);
        assertEquals(5, gameState.getClearedRowCount());

        gameState.addClearedRows(2);
        assertEquals(7, gameState.getClearedRowCount());

        gameState.addClearedRows(3);
        assertEquals(10, gameState.getClearedRowCount());

        gameState.addClearedRows(0);
        assertEquals(10, gameState.getClearedRowCount());
    }
    @Test
    void resetClearedRows() {
        gameState.addClearedRows(5);
        assertEquals(5, gameState.getClearedRowCount());

        gameState.resetClearedRows();
        assertEquals(0, gameState.getClearedRowCount());

        gameState.resetClearedRows();
        assertEquals(0, gameState.getClearedRowCount());
    }
    @Test
    void reset() {
        gameState.setGameOver(true);
        gameState.setPaused(true);
        gameState.addClearedRows(10);

        assertTrue(gameState.isGameOver());
        assertTrue(gameState.isPaused());
        assertEquals(10, gameState.getClearedRowCount());

        gameState.reset();

        assertFalse(gameState.isGameOver());
        assertFalse(gameState.isPaused());
        assertEquals(0, gameState.getClearedRowCount());

    }
}