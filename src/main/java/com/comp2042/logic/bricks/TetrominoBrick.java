package com.comp2042.logic.bricks;
import java.util.List;

public class TetrominoBrick implements Brick {
    private final TetrominoShapeType type;

    public TetrominoBrick(TetrominoShapeType type) {
        this.type = type;
    }

    @Override
    public List<int[][]> getShapeMatrix() {
        return type.getRotations();
    }
}
