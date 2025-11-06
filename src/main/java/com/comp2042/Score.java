package com.comp2042;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.rmi.Naming;

//tracks score and provides javafx property for binding
public final class Score {


    private final IntegerProperty score = new SimpleIntegerProperty(0);

    // JavaFX property to allow binding to UI elements
    public IntegerProperty scoreProperty() {
        return score;
    }

    //adds a given value to the score
    public void add(int i){
        score.setValue(score.getValue() + i);
    }

    //resets the score to zero
    public void reset() {
        score.setValue(0);
    }

    public Naming textProperty() {
        return null;
    }
}
