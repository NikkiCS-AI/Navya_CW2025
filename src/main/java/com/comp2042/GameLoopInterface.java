package com.comp2042;

public interface GameLoopInterface {
    void start(Runnable onTick);
    void pause();
    void resume();
    void stop();
    void setSpeed(double speedMultiplier);
    double getSpeed();
    boolean isRunning();
}

