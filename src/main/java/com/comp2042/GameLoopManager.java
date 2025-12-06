package com.comp2042;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameLoopManager implements GameLoopInterface {
    private Timeline timeLine;
// milliseconds

    private double speedMultiplier = 1.0;
    private final double BASE_SPEED = 500; // Base speed in milliseconds

    public void start(Runnable onTick) {
        if (timeLine != null) {
            timeLine.stop();
        }
        double speed = BASE_SPEED / speedMultiplier;
        timeLine = new Timeline(new KeyFrame(Duration.millis(speed), e -> onTick.run()));
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

    @Override
    public void setSpeed(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
        System.out.println("Setting speed multiplier: " + speedMultiplier);
    }

    @Override
    public double getSpeed() {
        return speedMultiplier;
    }

    public static double getSpeedForDifficulty(StartMenu.Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return 1.0; // Slower speed
            case MEDIUM:
                return 2.0; // Normal speed
            case HARD:
                return 3.0; // Faster speed
            default:
                return 1.0;
        }
    }

    public boolean isRunning() {
        return timeLine != null && timeLine.getStatus() == Timeline.Status.RUNNING;
    }
}
