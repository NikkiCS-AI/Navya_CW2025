package com.comp2042;

import com.comp2042.logic.bricks.Brick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrickRotatorTest {
    static class TestBrick implements Brick {
        private final List<int[][]> shapes;

        public TestBrick(int[][][] shapeArray) {
            this.shapes = Arrays.asList(shapeArray);
        }

        @Override
        public List<int[][]> getShapeMatrix() {
            return shapes;
        }
    }

    private BrickRotator rotator;

    @BeforeEach
    void setUp() {
        rotator = new BrickRotator();
    }

    @Test
    void getNextShape() {
        int[][][] shapes = {
            {
                {1, 0},
                {1, 0}
            },
            {
                {0, 1},
                {0, 1}
            },
            {
                {1, 1},
                {0, 0}
            }
        };
        TestBrick testBrick = new TestBrick(shapes);
        rotator.setBrick(testBrick);

        NextShapeInfo result1 = rotator.getNextShape();
        assertNotNull(result1);
        assertNotNull(result1.getShape());
        assertEquals(1, result1.getPosition());
    }

    @Test
    void getCurrentShape() {
        int[][][] shapes = {
            {
                {1, 0},
                {1, 0}
            },
            {
                {0, 1},
                {0, 1}
            }
        };
        TestBrick testBrick = new TestBrick(shapes);
        rotator.setBrick(testBrick);

        int[][] currentShape = rotator.getCurrentShape();
        assertNotNull(currentShape);
        assertArrayEquals(shapes[0], currentShape);
    }
    @Test
    void setCurrentShape() {
        int[][][] shapes = {
                {
                    {1, 0},
                    {1, 0}
                },
                {
                    {0, 1},
                    {0, 1}
                },
                {
                    {1, 1},
                    {0, 0}
                }
        };

        TestBrick testBrick = new TestBrick(shapes);
        rotator.setBrick(testBrick);

        rotator.setCurrentShape(1);
        int[][] currentShape = rotator.getCurrentShape();
        assertArrayEquals(shapes[1], currentShape);
    }
    @Test
    void setBrick() {
        int[][][] shapes = {
                {
                    {1, 0},
                    {1, 0}
                },
                {
                    {0, 1},
                    {0, 1}
                }
        };
        TestBrick testBrick = new TestBrick(shapes);
        rotator.setBrick(testBrick);

        Brick assignedBrick = rotator.getBrick();
        assertSame(testBrick, assignedBrick);
    }
}