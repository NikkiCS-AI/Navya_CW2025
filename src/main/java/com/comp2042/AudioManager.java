package com.comp2042;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

public class AudioManager {

    private MediaPlayer backgroundMusic;
    private MediaPlayer lineClearSound;
    private MediaPlayer gameOverSound;

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
        System.out.println("Background music path: " + getClass().getResource("src/main/resources/Audio/mixkit-game-level-music-689.mp3"));

    }

    public void playBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(0.4);
            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE); // loop forever
            backgroundMusic.play();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    public void playLineClearSound() {
        if (lineClearSound != null) {
            lineClearSound.stop();
            lineClearSound.play();
        }
    }

    public void playGameOverSound() {
        if (gameOverSound != null) {
            gameOverSound.stop();
            gameOverSound.play();
        }
    }
}
