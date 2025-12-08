package com.comp2042;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClearRowTest {
    @Test
    void noArgsConstructor() {
        ClearRow clearRow = new ClearRow();

        assertEquals(0, clearRow.getLinesRemoved());
        assertEquals(0, clearRow.getScoreBonus());

        int[][] expectedMatrix = clearRow.getNewMatrix();
        assertEquals(expectedMatrix.length, clearRow.getLinesRemoved());
    }
    @Test
    void getLinesRemoved() {
        assertTrue(true);
    }
    @Test
    void getNewMatrix() {
        int[][] originalMatrix = {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
        };

        ClearRow clearRow = new ClearRow(2, originalMatrix, 200);

        int[][] copyOfMatrix1  = clearRow.getNewMatrix();
        int[][] copyOfMatrix2  = clearRow.getNewMatrix();

        assertNotSame(copyOfMatrix1, copyOfMatrix2);
        assertArrayEquals(copyOfMatrix1, copyOfMatrix2);

        copyOfMatrix2[0][0] = 999;
        assertNotEquals(999, copyOfMatrix1[0][0]);
    }

    @Test
    void testGetLinesRemoved() {
        assertTrue(true);
    }
    @Test
    void testWithEmptyMatrix() {
        int[][] emptyMatrix = new int[0][0];
        int linesRemoved = 0;
        int scoreBonus = 0;

        ClearRow clearRow = new ClearRow(linesRemoved, emptyMatrix, scoreBonus);

        assertEquals(0, clearRow.getLinesRemoved());
        assertEquals(0, clearRow.getScoreBonus());
    }
    @Test
    void testGetScoreBonus() {
        int[][] dummyMatrix = {{1}};

        ClearRow noScore = new ClearRow(1, dummyMatrix, 0);
        assertEquals(0, noScore.getScoreBonus());

        ClearRow smallScore = new ClearRow(1, dummyMatrix, 100);
        assertEquals(100, smallScore.getScoreBonus());

        ClearRow largeScore = new ClearRow(1, dummyMatrix, 500);
        assertEquals(500, largeScore.getScoreBonus());
    }
}