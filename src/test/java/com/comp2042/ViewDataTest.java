package com.comp2042;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ViewDataTest {

    @Test
    void testGetBrickData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ViewData(null, 0, 0, null);
        });
    }

    @Test
    void getxPosition() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ViewData(new int[][]{{1, 1}, {1, 1}}, -1, 0, new int[][]{{1}});
        });
    }

    @Test
    void getyPosition() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ViewData(new int[][]{{1, 1}, {1, 1}}, 0, -1, new int[][]{{1}});
        });
    }

    @Test
    void testGetNextBrickData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ViewData(new int[][]{{1, 1}, {1, 1}}, 0, 0, null);
        });
    }
}