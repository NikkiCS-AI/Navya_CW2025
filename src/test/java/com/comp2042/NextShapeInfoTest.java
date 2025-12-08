package com.comp2042;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NextShapeInfoTest {
    @Test
    void getShape() {
        int[][] testShape = {
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1}
        };

        NextShapeInfo Info = new NextShapeInfo(testShape,  0);

        int[][] result = Info.getShape();
        assertNotNull(result);
        assertArrayEquals(testShape, result);
    }
    @Test
    void getPosition() {
        int[][] testShape = {
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1}
        };

        NextShapeInfo Info = new NextShapeInfo(testShape,  5);

        int position = Info.getPosition();
        assertEquals(5, position);
    }
}