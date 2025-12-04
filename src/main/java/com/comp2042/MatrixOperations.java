package com.comp2042;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixOperations {


    //We don't want to instantiate this utility class
    private MatrixOperations(){

    }

    //checks if bricks collide with filled cells or board boundaries
    public static boolean intersect(final int[][] matrix, final int[][] brick, int x, int y) {
        for (int i = 0; i < brick.length; i++) { // i = rows in brick
            for (int j = 0; j < brick[i].length; j++) { // j = columns in brick
                if (brick[i][j] != 0) { // Check if this cell of the brick is filled
                    int targetY = y + i;
                    int targetX = x + j;

                    // Debug logging
                    // System.out.println("Checking brick cell [" + i + "," + j + "] at board [" + targetX + "," + targetY + "]");

                    // Check if below the board
                    if (targetY >= matrix.length) {
                        // System.out.println("Below board at Y=" + targetY + ", matrix height=" + matrix.length);
                        return true;
                    }

                    // Check if left or right of the board
                    if (targetX < 0 || targetX >= matrix[0].length) {
                        // System.out.println("Outside X bounds: " + targetX);
                        return true;
                    }

                    // Check if collides with existing brick (only if within positive Y)
                    if (targetY >= 0 && matrix[targetY][targetX] != 0) {
                        // System.out.println("Collision with existing brick at [" + targetX + "," + targetY + "]");
                        return true;
                    }
                }
            }
        }
        return false;
    }

//    //checks if the target position is out of the board bounds
//    private static boolean checkOutOfBound(int[][] matrix, int targetX, int targetY) {
//        boolean returnValue = true;
//        if (targetX >= 0 && targetY < matrix.length && targetX < matrix[targetY].length) {
//            returnValue = false;
//        }
//        return returnValue;
//    }

    //creates a deep copy of a 2D integer array to prevent shared references and avoid modification side effects
    public static int[][] copy(int[][] original) {
        int[][] myInt = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            int[] aMatrix = original[i];
            int aLength = aMatrix.length;
            myInt[i] = new int[aLength];
            System.arraycopy(aMatrix, 0, myInt[i], 0, aLength);
        }
        return myInt;
    }

    //permanently adds birck to board once it has landed
    public static int[][] merge(int[][] filledFields, int[][] brick, int x, int y) {
        int[][] copy = copy(filledFields);

        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[i].length; j++) {
                if (brick[i][j] != 0) {
                    int targetX = x + j;
                    int targetY = y + i;

                    // Only merge if within board bounds
                    if (targetY >= 0 && targetY < copy.length &&
                            targetX >= 0 && targetX < copy[0].length) {
                        copy[targetY][targetX] = brick[i][j];
                    }
                }
            }
        }
        return copy;
    }

    //checks and removes filled rows from the board and returns updated board and score bonus
    public static ClearRow checkRemoving(final int[][] matrix) {
        int[][] tmp = new int[matrix.length][matrix[0].length];
        Deque<int[]> newRows = new ArrayDeque<>();
        List<Integer> clearedRows = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            int[] tmpRow = new int[matrix[i].length];
            boolean rowToClear = true;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowToClear = false;
                }
                tmpRow[j] = matrix[i][j];
            }
            if (rowToClear) {
                clearedRows.add(i);
                System.out.println("Row " + i + " will be cleared (completely filled)");
            } else {
                newRows.add(tmpRow);
            }
        }
        for (int i = matrix.length - 1; i >= 0; i--) {
            int[] row = newRows.pollLast();
            if (row != null) {
                tmp[i] = row;
            } else {
                tmp[i] = new int[matrix[0].length];
            }
        }
        int scoreBonus = 50 * clearedRows.size() * clearedRows.size();
        System.out.println("Cleared " + clearedRows.size() + " rows, score bonus: " + scoreBonus);
        return new ClearRow(clearedRows.size(), tmp, scoreBonus);
    }

    //creates a deep copy of a list of 2D integer arrays to prevent shared references and avoid modification side effects
    public static List<int[][]> deepCopyList(List<int[][]> list){
        return list.stream().map(MatrixOperations::copy).collect(Collectors.toList());
    }

}
