package com.comp2042;

/**
 *
 * <p>This class represents the result of clearing rows in a Tetris game.
 * It contains information about the number of lines removed, the new
 * state of the game board matrix after the rows have been cleared, and
 * the score bonus achieved by clearing those rows.</p>
 */

/** Represents the result of clearing rows in Tetris. */
public class ClearRow {

    private final int linesRemoved;
    private final int[][] newMatrix;
    private final int scoreBonus;

    /** No-argument constructor for empty clear row */
    public ClearRow() {
        this.linesRemoved = 0;
        this.newMatrix = new int[0][0];
        this.scoreBonus = 0;
    }

    /** Represents the result of clearing rows in Tetris and creates a new clear row object */
    public ClearRow(int linesRemoved, int[][] newMatrix, int scoreBonus) {
        this.linesRemoved = linesRemoved;
        this.newMatrix = newMatrix;
        this.scoreBonus = scoreBonus;
    }

    /** Return the number of rows removed */
    public int getLinesRemoved() {
        return linesRemoved;
    }

    /** Return the copy of new matrix after rows have been cleared */
    public int[][] getNewMatrix() {
        return MatrixOperations.copy(newMatrix);
    }

    /** Returns the score bonus achieved by clearing the rows */
    public int getScoreBonus() {
        return scoreBonus;
    }
}