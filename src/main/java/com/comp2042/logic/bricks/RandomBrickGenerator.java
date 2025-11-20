package com.comp2042.logic.bricks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBrickGenerator implements BrickGenerator {

    private final TetrominoShapeType[] types = TetrominoShapeType.values();
    private final Deque<TetrominoBrick> nextBricks = new ArrayDeque<>();

    public RandomBrickGenerator() {
        nextBricks.add(randomBrick());
        nextBricks.add(randomBrick());
    }

    private TetrominoBrick randomBrick() {
        TetrominoShapeType type =
                types[ThreadLocalRandom.current().nextInt(types.length)];
        return new TetrominoBrick(type);
    }

    @Override
    public Brick getBrick() {
        if (nextBricks.size() <= 1) {
            nextBricks.add(randomBrick());
        }
        return nextBricks.poll();
    }

    @Override
    public Brick getNextBrick() {
        return nextBricks.peek();
    }
}
