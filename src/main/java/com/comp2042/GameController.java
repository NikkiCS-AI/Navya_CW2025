package com.comp2042;

import com.comp2042.logic.bricks.Brick;
import com.comp2042.logic.bricks.BrickGenerator;
import com.comp2042.logic.bricks.RandomBrickGenerator;

public class GameController implements InputEventListener {

    private final Board board = new SimpleBoard(10, 24);
    private final GuiController viewGuiController;
    private HoldBrick hold;
    private boolean gameOver = false;

    // Connects the game logic with the GUI
    public GameController(GuiController c) {
        this.viewGuiController = c;
        c.setEventListener(this);

        // Create first brick
        boolean isGameOver = board.createNewBrick();
        if (isGameOver) {
            System.out.println("GAME OVER on initial spawn!");
            gameOver = true;
            viewGuiController.gameOver();
            return;
        }

        hold = new HoldBrick(new RandomBrickGenerator());

        c.initGameView(board.getBoardMatrix(), board.getViewData());
        c.bindScore(board.getScore().scoreProperty());

        // Update next brick preview
        updateNextBrickPreview();

        System.out.println("GameController initialized. Game over: " + gameOver);
    }

    // Handles the brick falling down
    @Override
    public DownData onDownEvent(MoveEvent event) {
        if (gameOver) {
            System.out.println("Game is over - ignoring down event");
            return new DownData(new ClearRow(), board.getViewData());
        }

        System.out.println("\n=== GameController.onDownEvent ===");
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = new ClearRow();

        if (!canMove) {
            System.out.println("Brick cannot move down - merging to board");

            // Merge current brick to board
            board.mergeBrickToBackground();

            // Check if bricks reached the spawn area
            if (((SimpleBoard)board).checkGameOver()) {
                System.out.println("GAME OVER: Bricks reached the spawn area!");
                gameOver = true;
                viewGuiController.gameOver();
                return new DownData(clearRow, board.getViewData());
            }

            // Clear completed rows
            clearRow = board.clearRows();
            hold.resetHoldAvailability();

            if (clearRow.getLinesRemoved() > 0) {
                System.out.println("Cleared " + clearRow.getLinesRemoved() + " rows");
                board.getScore().add(clearRow.getScoreBonus());
            }

            // Create new brick and check for game over
            boolean isGameOver = board.createNewBrick();
            if (isGameOver) {
                System.out.println("GAME OVER: Cannot spawn new brick!");
                gameOver = true;
                viewGuiController.gameOver();
            }

            // Refresh background to show merged bricks
            viewGuiController.refreshGameBackground(board.getBoardMatrix());

            // Update next brick preview
            updateNextBrickPreview();

            System.out.println("Game over state: " + gameOver);
        }

        return new DownData(clearRow, board.getViewData());
    }

    // Moves brick to left
    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        if (gameOver) {
            System.out.println("Game is over - ignoring left event");
            return board.getViewData();
        }
        board.moveBrickLeft();
        return board.getViewData();
    }

    // Moves brick to right
    @Override
    public ViewData onRightEvent(MoveEvent event) {
        if (gameOver) {
            System.out.println("Game is over - ignoring right event");
            return board.getViewData();
        }
        board.moveBrickRight();
        return board.getViewData();
    }

    // Rotates brick
    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        if (gameOver) {
            System.out.println("Game is over - ignoring rotate event");
            return board.getViewData();
        }
        board.rotateLeftBrick();
        return board.getViewData();
    }

    // Starts a new game
    @Override
    public void createNewGame() {
        System.out.println("\n=== Creating New Game ===");
        gameOver = false;
        board.newGame();
        hold = new HoldBrick(new RandomBrickGenerator());
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
        updateNextBrickPreview();
        System.out.println("New game started. Game over: " + gameOver);
    }

    @Override
    public void onHoldEvent() {
        if (gameOver) {
            System.out.println("Game is over - ignoring hold event");
            return;
        }
        handleHold();
    }

    @Override
    public int[][] getHoldShape() {
        if (hold != null && hold.getHeldBrick() != null) {
            return hold.getHeldBrick().getShapeMatrix().get(0);
        }
        return new int[0][];
    }

    private void handleHold() {
        // 1. Get the brick currently falling on the board
        Brick currentBrick = board.getCurrentBrick();
        if (currentBrick == null) {
            System.out.println("No current brick to hold");
            return;
        }

        // 2. Try to hold/swap
        Brick swapped = hold.hold(currentBrick);
        if (swapped == null) {
            System.out.println("Hold failed - swapped brick is null");
            return;
        }

        // 3. Apply the newly active brick
        board.setCurrentBrick(swapped);

        // 4. Update the GUI hold panel
        Brick held = hold.getHeldBrick();
        if (held != null) {
            viewGuiController.updateHoldDisplay(held.getShapeMatrix().get(0));
        }

        // Center/update display of falling brick
        refreshFallingBrick();
        System.out.println("Brick held successfully");
    }

    private void refreshFallingBrick() {
        viewGuiController.refreshBrick(board.getViewData());
    }

    public ViewData getCurrentViewData() {
        return board.getViewData();
    }

    private void updateNextBrickPreview() {
        ViewData viewData = board.getViewData();
        if (viewData != null && viewData.getNextBrickData() != null) {
            ViewData nextView = new ViewData(
                    viewData.getNextBrickData(),
                    0, 0,
                    null
            );
            viewGuiController.updateNextBrick(nextView);
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}