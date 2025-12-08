package com.comp2042;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DownDataTest {
    @Test
    void getClearRow() {
        ClearRow clearRow = new ClearRow(2, new int[10][20], 300);
        int[][] brickShape = {
            {1, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        ViewData viewData = new ViewData(brickShape, 4, 0, null);
        DownData downData = new DownData(clearRow, viewData);

        ClearRow result = downData.getClearRow();
        assertSame(clearRow, result);
        assertEquals(2, result.getLinesRemoved());
    }

    @Test
    void getViewData() {
        ClearRow clearRow = new ClearRow(2, new int[10][20], 300);
        int[][] brickShape = {
            {1, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

        ViewData viewData = new ViewData(brickShape, 4, 0, null);
        DownData downData = new DownData(clearRow, viewData);

        ViewData result = downData.getViewData();
        assertSame(viewData, result);
        assertEquals(4, result.getxPosition());
        assertEquals(0, result.getyPosition());

    }

}