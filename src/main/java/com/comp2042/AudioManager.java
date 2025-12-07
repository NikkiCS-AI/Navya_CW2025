package com.comp2042;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

/**
 * Manages audio playback for the game, including background music and sound effects.
 *<p> This class loads audio files from the resources folder and provides methods to
 * play, stop and loop different soundtracks. It uses {@link MediaPlayer} from javafx for audio playback.</p>
 */

public class AudioManager implements AudioManagerInterface {

    private MediaPlayer backgroundMusic;
    private MediaPlayer lineClearSound;
    private MediaPlayer gameOverSound;

    /** Constructor that initializes the audio manager by loading audio files.
     * It sets up background music to loop indefinitely and prepares sound effects for line clear and game over events.
     * @throws Exception if audio files cannot be loaded.
     */

    public AudioManager() {
        try {
            URL bgUrl = getClass().getResource("/Audio/mixkit-game-level-music-689.mp3");
            System.out.println("Background music URL: " + bgUrl);

            backgroundMusic = new MediaPlayer(new Media(bgUrl.toExternalForm()));
            backgroundMusic.setOnEndOfMedia(() -> backgroundMusic.seek(Duration.ZERO));

            URL winUrl = getClass().getResource("/Audio/mixkit-winning-a-coin-video-game-2069.mp3");
            URL loseUrl = getClass().getResource("/Audio/mixkit-player-losing-or-failing-2042.mp3");

            lineClearSound = new MediaPlayer(new Media(winUrl.toExternalForm()));
            gameOverSound = new MediaPlayer(new Media(loseUrl.toExternalForm()));

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading audio files: " + e.getMessage());
        }
        System.out.println("Background music path: " + getClass().getResource("/Audio/mixkit-game-level-music-689.mp3"));

    }

    /** Plays the background music in a loop at a set volume. */

    public void playBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(0.4);
            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE); // loop forever
            backgroundMusic.play();
        }
    }

    /** Stops the background music playback. */
    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    /** Plays the line clear sound effect.
     * <p>  The method ensures that the sound plays by calling {@link MediaPlayer#stop} before {@link MediaPlayer#play}</p>*/

    public void playLineClearSound() {
        if (lineClearSound != null) {
            lineClearSound.stop();
            lineClearSound.play();
        }
    }

    /**
     * Plays the game-over sound effect.
     * <p>
     * The sound is restarted on each call by stopping prior playback first.
     */

    public void playGameOverSound() {
        if (gameOverSound != null) {
            gameOverSound.stop();
            gameOverSound.play();
        }
    }
}
