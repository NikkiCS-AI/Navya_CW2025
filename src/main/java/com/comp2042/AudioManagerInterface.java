package com.comp2042;
/**
 *
 * <p>This interface provides methods for controlling background music
 * and playing sound effects associated with game events. Implementations
 * of this interface should handle audio loading, playback, looping,
 * and stopping of sound files.</p>
 */
public interface AudioManagerInterface {
    /** Plays the background music in a loop. */
    void playBackgroundMusic();
    /** Stops the background music playback. */
    void stopBackgroundMusic();
    /** Plays the sound effect for line clearing. */
    void playLineClearSound();
    /** Plays the sound effect for game over event. */
    void playGameOverSound();
}

