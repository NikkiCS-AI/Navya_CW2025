package com.comp2042;

public final class ClearRow {

    private final int linesRemoved;
    private final int[][] newMatrix;
    private final int scoreBonus;

    // represents the result of clearing rows in Tetris and creates a new clear row object
    public ClearRow(int linesRemoved, int[][] newMatrix, int scoreBonus) {
        this.linesRemoved = linesRemoved;
        this.newMatrix = newMatrix;
        this.scoreBonus = scoreBonus;
    }

    // return the number of rows removed
    public int getLinesRemoved() {
        return linesRemoved;
    }

    // return the copy of new matrix after rows have been cleared
    public int[][] getNewMatrix() {
        return MatrixOperations.copy(newMatrix);
    }

    //returns the score bonus achieved by clearing the rows
    public int getScoreBonus() {
        return scoreBonus;
    }
}
