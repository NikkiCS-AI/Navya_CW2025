package com.comp2042;

import com.comp2042.logic.bricks.Brick;
import com.comp2042.logic.bricks.BrickGenerator;
import com.comp2042.logic.bricks.RandomBrickGenerator;

public class GameController implements InputEventListener {

    private Board board = new SimpleBoard(25, 10);

    private final GuiController viewGuiController;
    
    private HoldBrick Hold;

    //connects the game logic with the GUI
    public GameController(GuiController c) {
        viewGuiController = c;
        board.createNewBrick();
            Hold = new HoldBrick(new RandomBrickGenerator());

        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
    }

    //hanfles the brick falling down
    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        if (!canMove) {
            board.mergeBrickToBackground();
            clearRow = board.clearRows();
            Hold.resetHoldAvailability();

            if (clearRow.getLinesRemoved() > 0) {
                board.getScore().add(clearRow.getScoreBonus());
            }
            if (board.createNewBrick()) {
                viewGuiController.gameOver();
            }

            viewGuiController.refreshGameBackground(board.getBoardMatrix());

            // --- Update next brick preview ---
            ViewData viewData = board.getViewData(); // includes nextBrickData
            if (viewData != null && viewData.getNextBrickData() != null) {
                ViewData nextView = new ViewData(
                        viewData.getNextBrickData(), // next brick shape
                        0, 0,                        // position irrelevant for preview
                        null                         // no need for nested next-next brick
                );
                viewGuiController.updateNextBrick(nextView);
            }


        } else {
            if (event.getEventSource() == EventSource.USER) {

            }
        }
        return new DownData(clearRow, board.getViewData());
    }

    //moves brick to left
    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        board.moveBrickLeft();
        return board.getViewData();
    }

    //moves brick to right
    @Override
    public ViewData onRightEvent(MoveEvent event) {
        board.moveBrickRight();
        return board.getViewData();
    }

    //rotates brick
    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        board.rotateLeftBrick();
        return board.getViewData();
    }


    //starts a new game
    @Override
    public void createNewGame() {
        board.newGame();
        Hold = new HoldBrick(new RandomBrickGenerator());
        viewGuiController.refreshGameBackground(board.getBoardMatrix());

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

    @Override
    public void onHoldEvent() {
        handleHold();
    }

    private void handleHold() {

        // 1. Get the brick currently falling on the board (INSTANCE, not class)
        Brick currentBrick = board.getCurrentBrick();

        // 2. Try to hold/swap
        Brick swapped = Hold.hold(currentBrick);

        // 3. If swapped is different, apply the newly active brick
        board.setCurrentBrick(swapped);


        // 4. Update the GUI hold panel
        Brick held = Hold.getHeldBrick();
        if (held != null) {
            viewGuiController.updateHoldDisplay(
                    held.getShapeMatrix().get(0)
            );
        }

        // Center/update display of falling brick
        refreshFallingBrick();
    }

    private void refreshFallingBrick() {
        viewGuiController.refreshBrick(board.getViewData());
    }



}
