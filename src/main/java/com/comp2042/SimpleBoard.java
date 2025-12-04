package com.comp2042;

import com.comp2042.logic.bricks.Brick;
import com.comp2042.logic.bricks.BrickGenerator;
import com.comp2042.logic.bricks.RandomBrickGenerator;

import java.awt.*;

public class SimpleBoard implements Board {
    private final int width;
    private final int height;
    private final BrickGenerator brickGenerator;
    private final BrickRotator brickRotator;
    private int[][] currentGameMatrix;
    private Point currentOffset;
    private final Score score;

    // Use same constants as renderers
    private static final int VISIBLE_START_ROW = GridRenderer.VISIBLE_START_ROW;

    public SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;

        System.out.println("\n=== BOARD CONSTRUCTOR ===");
        System.out.println("Board dimensions: " + width + "x" + height);
        System.out.println("Visible rows: " + VISIBLE_START_ROW + " to " + (height - 1));

        currentGameMatrix = new int[height][width];

        // Initialize all cells to 0 (empty)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                currentGameMatrix[i][j] = 0;
            }
        }

        System.out.println("Matrix created: " + currentGameMatrix.length +
                " rows x " + currentGameMatrix[0].length + " cols");

        brickGenerator = new RandomBrickGenerator();
        brickRotator = new BrickRotator();
        score = new Score();

        System.out.println("=== END BOARD CONSTRUCTOR ===\n");
    }

    @Override
    public boolean moveBrickDown() {
        int[][] brickShape = brickRotator.getCurrentShape();
        Point p = new Point(currentOffset);
        p.translate(0, 1);

        Debugger.debugBrickMovement(brickShape, currentOffset, p.y, currentGameMatrix);

        // Check if any brick cell would go below the matrix
        for (int i = 0; i < brickShape.length; i++) {
            for (int j = 0; j < brickShape[i].length; j++) {
                if (brickShape[i][j] != 0) {
                    int targetY = p.y + i;

                    // Stop if ANY brick cell would be at or below the bottom row
                    if (targetY >= currentGameMatrix.length) {
                        Debugger.log("STOPPING: Brick cell would go below matrix at Y=" + targetY);
                        return false;
                    }
                }
            }
        }

        // Check collision with other bricks
        boolean conflict = MatrixOperations.intersect(currentGameMatrix, brickShape,
                (int) p.getX(), (int) p.getY());

        Debugger.log("Collision check: " + conflict);

        if (conflict) {
            return false;
        } else {
            currentOffset = p;
            Debugger.log("Moved to: " + currentOffset);
            return true;
        }
    }

    @Override
    public boolean moveBrickLeft() {
        int[][] currentMatrix = MatrixOperations.copy(currentGameMatrix);
        Point p = new Point(currentOffset);
        p.translate(-1, 0);
        boolean conflict = MatrixOperations.intersect(currentMatrix, brickRotator.getCurrentShape(),
                (int) p.getX(), (int) p.getY());
        if (conflict) {
            return false;
        } else {
            currentOffset = p;
            return true;
        }
    }

    @Override
    public boolean moveBrickRight() {
        int[][] currentMatrix = MatrixOperations.copy(currentGameMatrix);
        Point p = new Point(currentOffset);
        p.translate(1, 0);
        boolean conflict = MatrixOperations.intersect(currentMatrix, brickRotator.getCurrentShape(),
                (int) p.getX(), (int) p.getY());
        if (conflict) {
            return false;
        } else {
            currentOffset = p;
            return true;
        }
    }

    @Override
    public boolean rotateLeftBrick() {
        int[][] currentMatrix = MatrixOperations.copy(currentGameMatrix);
        NextShapeInfo nextShape = brickRotator.getNextShape();
        boolean conflict = MatrixOperations.intersect(currentMatrix, nextShape.getShape(),
                (int) currentOffset.getX(), (int) currentOffset.getY());
        if (conflict) {
            return false;
        } else {
            brickRotator.setCurrentShape(nextShape.getPosition());
            return true;
        }
    }

    @Override
    public boolean createNewBrick() {
        Brick currentBrick = brickGenerator.getBrick();
        brickRotator.setBrick(currentBrick);

        // Spawn position (top center)
        currentOffset = new Point(4, 0);

        // Get the brick shape
        int[][] brickShape = brickRotator.getCurrentShape();

        System.out.println("\n=== Creating New Brick ===");
        System.out.println("Brick shape height: " + brickShape.length);
        System.out.println("Spawn position: " + currentOffset);

        // CRITICAL: Check for collision at spawn position (GAME OVER CONDITION)
        boolean collisionAtSpawn = MatrixOperations.intersect(
                currentGameMatrix,
                brickShape,
                currentOffset.x,
                currentOffset.y
        );

        if (collisionAtSpawn) {
            System.out.println("GAME OVER: Brick collides at spawn position!");

            // Debug: Show spawn area
            System.out.println("Spawn area (rows 0-3):");
            for (int r = 0; r < Math.min(4, currentGameMatrix.length); r++) {
                System.out.print("Row " + r + ": ");
                for (int c = 0; c < width; c++) {
                    System.out.print(currentGameMatrix[r][c] + " ");
                }
                System.out.println();
            }

            return true; // Game over
        }

        // Also check if brick fits within board bounds
        for (int r = 0; r < brickShape.length; r++) {
            for (int c = 0; c < brickShape[r].length; c++) {
                if (brickShape[r][c] != 0) {
                    int boardY = currentOffset.y + r;
                    int boardX = currentOffset.x + c;

                    if (boardY < 0 || boardY >= currentGameMatrix.length ||
                            boardX < 0 || boardX >= currentGameMatrix[0].length) {
                        System.out.println("GAME OVER: Brick doesn't fit on board at [" +
                                boardX + "," + boardY + "]");
                        return true;
                    }
                }
            }
        }

        System.out.println("New brick created successfully");
        debugCurrentBrick();

        return false;
    }

    @Override
    public void mergeBrickToBackground() {
        System.out.println("\n=== mergeBrickToBackground ===");
        int[][] brickShape = brickRotator.getCurrentShape();
        System.out.println("Merging brick at X=" + currentOffset.x + ", Y=" + currentOffset.y);
        System.out.println("Brick top is at row: " + currentOffset.y);

        // DEBUG: Check if merging in spawn area
        if (currentOffset.y < 4) {
            System.out.println("WARNING: Brick merging in spawn area (rows 0-3)!");
        }

        // Use MatrixOperations.merge for proper merging
        currentGameMatrix = MatrixOperations.merge(currentGameMatrix, brickShape,
                (int) currentOffset.getX(), (int) currentOffset.getY());

        // DEBUG: Show board state after merge
        debugMatrix();
    }

    public void setCurrentBrick(Brick b) {
        brickRotator.setBrick(b);
        currentOffset = new Point(4, 0);
    }

    @Override
    public int[][] getBoardMatrix() {
        return currentGameMatrix;
    }

    @Override
    public ViewData getViewData() {
        Brick nextBrick = brickGenerator.getNextBrick();
        int[][] nextBrickData = null;
        if (nextBrick != null && nextBrick.getShapeMatrix() != null && !nextBrick.getShapeMatrix().isEmpty()) {
            nextBrickData = nextBrick.getShapeMatrix().get(0);
        }
        return new ViewData(brickRotator.getCurrentShape(),
                (int) currentOffset.getX(),
                (int) currentOffset.getY(),
                nextBrickData);
    }

    @Override
    public ClearRow clearRows() {
        System.out.println("\n=== clearRows ===");
        ClearRow clearRow = MatrixOperations.checkRemoving(currentGameMatrix);
        System.out.println("Cleared " + clearRow.getLinesRemoved() + " rows");
        currentGameMatrix = clearRow.getNewMatrix();

        // DEBUG: Show board after clearing
        if (clearRow.getLinesRemoved() > 0) {
            debugMatrix();
        }

        return clearRow;
    }

    @Override
    public Score getScore() {
        return score;
    }

    @Override
    public void newGame() {
        System.out.println("\n=== Starting New Game ===");
        // Clear the entire board
        currentGameMatrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                currentGameMatrix[i][j] = 0;
            }
        }
        score.reset();
        createNewBrick();
    }

    public Brick getCurrentBrick() {
        return brickRotator.getBrick();
    }

    public void debugCurrentBrick() {
        int[][] brickShape = brickRotator.getCurrentShape();
        Debugger.debugBrickPosition(brickShape, currentOffset, currentGameMatrix);
    }

    // Helper method to check if game should end (check spawn area rows 0-3)
    public boolean checkGameOver() {
        // Check if any cell in the top few rows (spawn area) is filled
        for (int r = 0; r < 4; r++) { // Check rows 0-3 (spawn area)
            for (int c = 0; c < width; c++) {
                if (currentGameMatrix[r][c] != 0) {
                    System.out.println("Game over detected at [" + r + "," + c + "] = " +
                            currentGameMatrix[r][c]);
                    return true;
                }
            }
        }
        return false;
    }

    // Debug method to show matrix state
    public void debugMatrix() {
        System.out.println("=== BOARD MATRIX (first 10 rows) ===");
        for (int r = 0; r < Math.min(10, currentGameMatrix.length); r++) {
            System.out.print("Row " + r + ": ");
            for (int c = 0; c < currentGameMatrix[r].length; c++) {
                System.out.print(currentGameMatrix[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("=== END BOARD DEBUG ===\n");
    }
}