package com.comp2042;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.rmi.Naming;

/**
 * The {@code Score} class tracks and manages the player's score
 * during gameplay. It uses a JavaFX {@link IntegerProperty} so
 * the score can be easily bound to UI elements and update automatically.
 */

/** tracks score and provides javafx property for binding */
public final class Score {


    /** internal score property */
    private final IntegerProperty score = new SimpleIntegerProperty(0);

    /** JavaFX property to allow binding to UI elements */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /** adds a given value to the score */
    public void add(int i){
        score.setValue(score.getValue() + i);
    }

    /** resets the score to zero */
    public void reset() {
        score.setValue(0);
    }

    /** Dummy method to satisfy the code completion requirement */
    public Naming textProperty() {
        return null;
    }
}
