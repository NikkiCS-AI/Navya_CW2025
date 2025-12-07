package com.comp2042;
/** Interface for managing the game loop, including starting, pausing, resuming, stopping, and adjusting speed.
*/
public interface GameLoopInterface {
    /** starts the game loop, calling onTick Runnable at each tick */
    void start(Runnable onTick);

    /** pauses the game loop */
    void pause();

    /** resumes the game loop */
    void resume();

    /** stops the game loop */
    void stop();

    /** sets the speed multiplier for the game loop */
    void setSpeed(double speedMultiplier);

    /** gets the current speed multiplier of the game loop */
    double getSpeed();

    /** checks if the game loop is currently running */
    boolean isRunning();
}

