package com.comp2042.logic.bricks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A brick generator that generates random Tetromino bricks.
 */

public class RandomBrickGenerator implements BrickGenerator {

    private final TetrominoShapeType[] types = TetrominoShapeType.values();
    private final Deque<TetrominoBrick> nextBricks = new ArrayDeque<>();

    /**
     * Constructs a RandomBrickGenerator and initializes the next bricks queue.
     */
    public RandomBrickGenerator() {
        nextBricks.add(randomBrick());
        nextBricks.add(randomBrick());
    }

    /**
     * Generates a random Tetromino brick.
     *
     * @return A randomly generated TetrominoBrick.
     */
    private TetrominoBrick randomBrick() {
        TetrominoShapeType type =
                types[ThreadLocalRandom.current().nextInt(types.length)];
        return new TetrominoBrick(type);
    }

    @Override
    /**
     * Retrieves and removes the next brick from the generator.
     * If the queue has one or fewer bricks, a new random brick is added.
     *
     * @return The next TetrominoBrick.
     */
    public Brick getBrick() {
        if (nextBricks.size() <= 1) {
            nextBricks.add(randomBrick());
        }
        return nextBricks.poll();
    }

    @Override
    /**
     * Peeks at the next brick without removing it from the generator.
     *
     * @return The next TetrominoBrick.
     */
    public Brick getNextBrick() {
        return nextBricks.peek();
    }
}
