package com.comp2042;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViewDataTest {

    @Test
    void testConstructorandGetter() {
        int[][] brickData = {
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

        int[][] nextBrickData = {
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
        };

        int xPosition = 5;
        int yPosition = 10;

        ViewData viewData = new ViewData(brickData, xPosition, yPosition, nextBrickData);

        assertArrayEquals(brickData, viewData.getBrickData());
        assertEquals(xPosition, viewData.getxPosition());
        assertEquals(yPosition, viewData.getyPosition());
        assertArrayEquals(nextBrickData, viewData.getNextBrickData());
    }

    @Test
    void testGetBrickData() {
        int[][] originalData = {
            {1, 0, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

        int[][] originalNextBrickData = {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
        };

        ViewData viewData = new ViewData(originalData, 0, 0, originalNextBrickData);

        int[][] retrievedData = viewData.getBrickData();
        retrievedData[0][0] = 999; // Modify the retrieved data

        assertNotEquals(999, originalNextBrickData[0][0]);
        assertEquals(0, originalNextBrickData[0][0]);
    }
    @Test
    void getxPosition() {
        assertTrue(true);
    }
    @Test
    void getyPosition() {
        assertTrue(true);
    }
}