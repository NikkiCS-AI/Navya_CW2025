package com.comp2042;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static java.awt.Color.getColor;

public class BrickColour {
        // assigns colors to the different tetris bricks
        public static Paint getColour(int i) {
            switch (i) {
                case 0:
                    return Color.TRANSPARENT;

                case 1:
                    return Color.AQUA;

                case 2:
                    return Color.BLUEVIOLET;

                case 3:
                    return Color.DARKGREEN;

                case 4:
                    return Color.YELLOW;

                case 5:
                    return Color.RED;

                case 6:
                    return Color.BEIGE;

                case 7:
                    return Color.BURLYWOOD;

                default:
                    return Color.WHITE;

            }
        }
    }


