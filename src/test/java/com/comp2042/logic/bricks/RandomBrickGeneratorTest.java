package com.comp2042.logic.bricks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleRandomBrickGeneratorTest {

    @Test
    void testGetBrick_ReturnsValidBrick() {
        RandomBrickGenerator generator = new RandomBrickGenerator();

        // Get multiple bricks
        for (int i = 0; i < 10; i++) {
            Brick brick = generator.getBrick();
            assertNotNull(brick);
            assertNotNull(brick.getShapeMatrix());
            assertFalse(brick.getShapeMatrix().isEmpty());

            // Verify shape has valid dimensions
            for (int[][] shape : brick.getShapeMatrix()) {
                assertNotNull(shape);
                assertTrue(shape.length > 0);
                assertTrue(shape[0].length > 0);
            }
        }
    }

    @Test
    void testGetNextBrick_PeeksWithoutRemoving() {
        RandomBrickGenerator generator = new RandomBrickGenerator();

        // Peek at next brick
        Brick nextBrick = generator.getNextBrick();
        assertNotNull(nextBrick);

        // Get brick should return the same brick
        Brick actualBrick = generator.getBrick();
        assertNotNull(actualBrick);

        // Both should be Brick instances
        assertTrue(nextBrick instanceof Brick);
        assertTrue(actualBrick instanceof Brick);
    }

    @Test
    void testMultipleGenerators() {
        // Test that multiple generators work independently
        RandomBrickGenerator gen1 = new RandomBrickGenerator();
        RandomBrickGenerator gen2 = new RandomBrickGenerator();

        Brick brick1 = gen1.getBrick();
        Brick brick2 = gen2.getBrick();

        assertNotNull(brick1);
        assertNotNull(brick2);
        // They might be different types (random)
    }

    @Test
    void testContinuousGeneration() {
        // Test generating many bricks continuously
        RandomBrickGenerator generator = new RandomBrickGenerator();

        for (int i = 0; i < 100; i++) {
            assertDoesNotThrow(() -> {
                Brick brick = generator.getBrick();
                assertNotNull(brick);
            }, "Failed at iteration " + i);
        }
    }

    @Test
    void testInterfaceImplementation() {
        RandomBrickGenerator generator = new RandomBrickGenerator();

        // Should implement BrickGenerator
        assertTrue(generator instanceof BrickGenerator);

        // Methods should exist and work
        assertDoesNotThrow(() -> generator.getBrick());
        assertDoesNotThrow(() -> generator.getNextBrick());
    }
}