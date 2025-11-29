package com.comp2042;

public interface GameLoopInterface {
    void start(Runnable onTick);
    void pause();
    void resume();
    void stop();
    boolean isRunning();
}

