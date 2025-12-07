package com.comp2042;

/** interface for handling input events in the Tetris game*/

public interface InputEventListener {

    /** when the brick moves down, left, right or rotates, these methods are called*/
    DownData onDownEvent(MoveEvent event); /** moves down */

    ViewData onLeftEvent(MoveEvent event); /**moves left*/

    ViewData onRightEvent(MoveEvent event); /**moves right*/

    ViewData onRotateEvent(MoveEvent event); /**rotates*/

    void createNewGame(); /**creates new game*/

    void onHoldEvent(); /**holds the brick*/

    int[][] getHoldShape(); /** gets the hold shape*/

    /** handles hard drop */
    void onHardDropEvent();
}
