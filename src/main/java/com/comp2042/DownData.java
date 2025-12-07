package com.comp2042;

/**
 *
 * <p>This class encapsulates the data related to a downward movement
 * action in a Tetris game. It contains information about any cleared
 * rows as a result of the downward movement and the updated view data
 * reflecting the new state of the game board.</p>
 */

/** Encapsulates data for a downward movement action in Tetris. */
public final class DownData {
    private final ClearRow clearRow;
    private final ViewData viewData;

    /** stores cleared row and updated view data for down action */
    public DownData(ClearRow clearRow, ViewData viewData) {

        this.clearRow = clearRow;
        this.viewData = viewData;
    }

    /** returns cleared row */
    public ClearRow getClearRow() {
        return clearRow;
    }

    /** returns updated view data */
    public ViewData getViewData() {
        return viewData;
    }
}
