package com.comp2042.logic.bricks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleTetrominoBrickTest {

    @Test
    void testCreateBrick() {
        TetrominoBrick brick = new TetrominoBrick(TetrominoShapeType.I);
        assertNotNull(brick);
    }

    @Test
    void testAllTetrominoTypes() {
        // Test all 7 Tetromino types
        TetrominoShapeType[] types = TetrominoShapeType.values();
        assertEquals(7, types.length, "Should have 7 Tetromino types");

        for (TetrominoShapeType type : types) {
            TetrominoBrick brick = new TetrominoBrick(type);
            assertNotNull(brick.getShapeMatrix());
        }
    }

    @Test
    void testImplementsInterface() {
        TetrominoBrick brick = new TetrominoBrick(TetrominoShapeType.T);
        assertTrue(brick instanceof Brick);
    }
}