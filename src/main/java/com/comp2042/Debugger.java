package com.comp2042;

import java.awt.Point;

public class Debugger {

    public static void debugBrickMovement(int[][] brickShape, Point currentOffset, int newY, int[][] matrix) {
        System.out.println("\n=== DEBUG Brick Movement ===");
        System.out.println("Current position: (" + currentOffset.x + "," + currentOffset.y + ")");
        System.out.println("Moving to Y=" + newY);
        System.out.println("Brick shape: " + brickShape.length + "x" + brickShape[0].length);

        // Check spawn area
        System.out.println("Spawn area (rows 0-3):");
        for (int r = 0; r < Math.min(4, matrix.length); r++) {
            System.out.print("Row " + r + ": ");
            for (int c = 0; c < Math.min(10, matrix[r].length); c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }

        System.out.println("=== END DEBUG ===\n");
    }

    public static void debugBrickPosition(int[][] brickShape, Point offset, int[][] matrix) {
        System.out.println("\n=== DEBUG Brick Position ===");
        System.out.println("Brick at (" + offset.x + "," + offset.y + ")");

        for (int r = 0; r < brickShape.length; r++) {
            System.out.print("Brick row " + r + ": ");
            for (int c = 0; c < brickShape[r].length; c++) {
                System.out.print(brickShape[r][c] + " ");
            }
            System.out.println();
        }

        // Show where brick would be on board
        System.out.println("Would occupy board rows " + offset.y + " to " + (offset.y + brickShape.length - 1));

        System.out.println("=== END DEBUG ===\n");
    }

    public static void log(String message) {
        System.out.println("[LOG] " + message);
    }
}