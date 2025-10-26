package com.comp2042;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DownDataTest {

    @BeforeEach

    @Test
    void getClearRow() {
        assertThrows(NullPointerException.class, () -> {
            DownData downData = new DownData(null, null);
            downData.getClearRow();
        });
    }

    @Test
    void getViewData() {
        assertThrows(NullPointerException.class, () -> {
            DownData downData = new DownData(null, null);
            downData.getViewData();
        });
    }
}