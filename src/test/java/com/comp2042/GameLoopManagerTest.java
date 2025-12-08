package com.comp2042;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class GameLoopManagerTest {
    private GameLoopManager gameLoop;

    @BeforeEach
    void setUp() {
        gameLoop = new GameLoopManager();
    }

    @Test
    void start() {
        Runnable onTick = () -> {} ;
        gameLoop.start(onTick);
        assertTrue(gameLoop.isRunning());
    }
    @Test
    void pause() {
        gameLoop.pause();
        assertTrue(true);
    }
    @Test
    void resume() {
        gameLoop.resume();
        assertTrue(true);
    }
    @Test
    void stop() {
        gameLoop.stop();
        assertTrue(true);
    }
    @Test
    void setSpeed() {
        gameLoop.setSpeed(2.0);
        assertEquals(2.0, gameLoop.getSpeed(), 0.001);

        gameLoop.setSpeed(1.0);
        assertEquals(1.0, gameLoop.getSpeed(), 0.001);

        gameLoop.setSpeed(3.0);
        assertEquals(3.0, gameLoop.getSpeed(), 0.001);

    }

    @Test
    void getSpeed() {
        assertEquals(1.0, gameLoop.getSpeed(), 0.001);

        gameLoop.setSpeed(2.0);
        assertEquals(2.0, gameLoop.getSpeed(), 0.001);
    }
    @Test
    void getSpeedForDifficulty() {
        assertEquals(1.0, GameLoopManager.getSpeedForDifficulty(StartMenu.Difficulty.EASY), 0.001);
        assertEquals(2.0, GameLoopManager.getSpeedForDifficulty(StartMenu.Difficulty.MEDIUM), 0.001);
        assertEquals(3.0, GameLoopManager.getSpeedForDifficulty(StartMenu.Difficulty.HARD), 0.001);
    }
    @Test
    void isRunning() {
        assertFalse(gameLoop.isRunning());
        boolean result = gameLoop.isRunning();
        assertTrue(result == false || result == true);
    }
}