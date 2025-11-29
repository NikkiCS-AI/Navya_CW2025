package com.comp2042;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameLoopManager implements GameLoopInterface {
    private Timeline timeLine;
    private static final double DEFAULT_SPEED = 400; // milliseconds

    public void start(Runnable onTick) {
        timeLine = new Timeline(new KeyFrame(
                Duration.millis(DEFAULT_SPEED),
                ae -> onTick.run()
        ));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    public void pause() {
        if (timeLine != null) {
            timeLine.pause();
        }
    }

    public void resume() {
        if (timeLine != null) {
            timeLine.play();
        }
    }

    public void stop() {
        if (timeLine != null) {
            timeLine.stop();
        }
    }

    public boolean isRunning() {
        return timeLine != null && timeLine.getStatus() == Timeline.Status.RUNNING;
    }
}
