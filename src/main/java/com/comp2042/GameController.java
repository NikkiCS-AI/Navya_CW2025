package com.comp2042;

import com.comp2042.logic.bricks.BrickGenerator;

public class GameController implements InputEventListener {

    private Board board = new SimpleBoard(25, 10);

    private final GuiController viewGuiController;

    //connects the game logic with the GUI
    public GameController(GuiController c) {
        viewGuiController = c;
        board.createNewBrick();
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
}
