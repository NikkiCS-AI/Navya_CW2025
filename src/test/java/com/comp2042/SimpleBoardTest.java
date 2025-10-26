package com.comp2042;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SimpleBoardTest {

    @Test
    void moveBrickDown() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(-1, 10);
        });
    }

    @Test
    void moveBrickLeft() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(25, -5);
        });
    }

    @Test
    void moveBrickRight() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(0, 0);
        });
    }

    @Test
    void rotateLeftBrick() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(-10, -10);
        });
    }

    @Test
    void createNewBrick() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(-20, 15);
        });
    }

    @Test
    void getBoardMatrix() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(10, -15);
        });
    }

    @Test
    void getViewData() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(-5, -5);
        });
    }

    @Test
    void mergeBrickToBackground() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(-1, -1);
        });
    }

    @Test
    void clearRows() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(-30, 20);
        });
    }

    @Test
    void getScore() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(0, -20);
        });
    }

    @Test
    void newGame() {
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleBoard board = new SimpleBoard(-15, 25);
        });
    }
}