package com.comp2042;

public interface Board {

    // returns if the brick was moved down successfully
    boolean moveBrickDown();

    // returns if the brick was moved left successfully
    boolean moveBrickLeft();

    // returns if the brick was moved right successfully
    boolean moveBrickRight();

    // returns if the brick was rotated left successfully
    boolean rotateLeftBrick();

    // returns if the new brick was created successfully
    boolean createNewBrick();

    // returns all the placed bricks
    int[][] getBoardMatrix();

    // returns the current brick
    ViewData getViewData();

    // merges the current brick to the background
    void mergeBrickToBackground();

    //  removes full rows and returns the number of cleared rows
    ClearRow clearRows();

    // returns the current score
    Score getScore();

    // resets the game state for a new game
    void newGame();
}
