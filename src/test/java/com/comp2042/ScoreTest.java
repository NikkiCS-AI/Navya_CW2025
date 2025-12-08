package com.comp2042;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ScoreTest {

    @Test
    void add() {
        Score score = new Score();
        IntegerProperty property = score.scoreProperty();

        score.add(100);
        assertEquals(100, property.get());

        score.add(50);
        assertEquals(150, property.get());
    }

    @Test
    void reset() {

        Score score = new Score();
        IntegerProperty property = score.scoreProperty();

        score.add(200);
        assertEquals(200, property.get());

        score.reset();
        assertEquals(0, property.get());
    }


    @Test
    void textProperty() {
        assertNull(new Score().textProperty());
    }
}