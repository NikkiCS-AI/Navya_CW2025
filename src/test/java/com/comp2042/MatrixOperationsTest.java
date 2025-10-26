package com.comp2042;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MatrixOperationsTest {

    @Test
    void intersect() {
        assertEquals(true, MatrixOperations.intersect(
                new int[][]{
                        {0, 0, 0, 0, 0},
                        {0, 1, 1, 0, 0},
                        {0, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                },
                new int[][]{
                        {1, 1},
                        {1, 1}
                },
                1,
                1
        ));
    }

    @Test
    void copy() {
        int[][] original = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        int[][] copied = MatrixOperations.copy(original);
        assertArrayEquals(original, copied);
        // Modify copied and check original remains unchanged
        copied[1][1] = 9;
        assertNotEquals(original[1][1], copied[1][1]);
    }

    @Test
    void merge() {
        int[][] filledFields = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        int[][] brick = {
                {2, 2},
                {2, 2}
        };
        int x = 1;
        int y = 2;
        int[][] expected = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 2, 2, 0},
                {0, 2, 2, 0}
        };
        int[][] result = MatrixOperations.merge(filledFields, brick, x, y);
        assertArrayEquals(expected, result);
    }

    @Test
    void checkRemoving() {
        assertDoesNotThrow(() -> MatrixOperations.checkRemoving(new int[][]{
                {1, 1, 1, 1},
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        }));
    }

    @Test
    void deepCopyList() {
        assertDoesNotThrow(() -> MatrixOperations.deepCopyList(
                List.of(
                        new int[][]{
                                {0, 0},
                                {1, 1}
                        },
                        new int[][]{
                                {2, 2},
                                {3, 3}
                        }
                )
        ));
    }
}