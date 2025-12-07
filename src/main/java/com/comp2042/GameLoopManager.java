package com.comp2042;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Manages the game loop using JavaFX Timeline.
 */

public class GameLoopManager implements GameLoopInterface {
    private Timeline timeLine;
// milliseconds

    private double speedMultiplier = 1.0;
    private final double BASE_SPEED = 500; // Base speed in milliseconds

    /** Starts the game loop with the specified onTick action.
     *
     * @param onTick The action to perform on each tick of the game loop.
     */
    public void start(Runnable onTick) {
        if (timeLine != null) {
            timeLine.stop();
        }
        double speed = BASE_SPEED / speedMultiplier;
        timeLine = new Timeline(new KeyFrame(Duration.millis(speed), e -> onTick.run()));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    /** Pauses the game loop. */
    public void pause() {
        if (timeLine != null) {
            timeLine.pause();
        }
    }

    /** Resumes the game loop if it was paused. */
    public void resume() {
        if (timeLine != null) {
            timeLine.play();
        }
    }

    /** Stops the game loop completely. */
    public void stop() {
        if (timeLine != null) {
            timeLine.stop();
        }
    }

    @Override
    /** Sets the speed multiplier for the game loop.
     *
     * @param speedMultiplier The speed multiplier to set.
     */
    public void setSpeed(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
        System.out.println("Setting speed multiplier: " + speedMultiplier);
    }

    @Override
    /** Gets the current speed multiplier.
     *
     * @return The current speed multiplier.
     */
    public double getSpeed() {
        return speedMultiplier;
    }

    /** Gets the speed multiplier based on the selected difficulty.
     *
     * @param difficulty The selected difficulty level.
     * @return The corresponding speed multiplier.
     */
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

    /** Checks if the game loop is currently running.
     *
     * @return True if the game loop is running, false otherwise.
     */
    public boolean isRunning() {
        return timeLine != null && timeLine.getStatus() == Timeline.Status.RUNNING;
    }
}
