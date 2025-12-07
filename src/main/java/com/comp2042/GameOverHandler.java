package com.comp2042;

/** Handles the game over state, including stopping the game loop,
   updating the game state, displaying the game over panel, and managing audio.
*/

public class GameOverHandler {
    private final AudioManagerInterface audioManager;
    private final GameLoopInterface gameLoop;
    private final GameStateInterface gameState;
    private final GameOverPanel gameOverPanel;

    /** Constructs a GameOverHandler with the specified dependencies.
     *
     * @param audioManager  the audio manager for handling sound effects
     * @param gameLoop      the game loop controller
     * @param gameState     the game state manager
     * @param gameOverPanel the panel to display on game over
     */
    public GameOverHandler(AudioManagerInterface audioManager,
                           GameLoopInterface gameLoop,
                           GameStateInterface gameState,
                           GameOverPanel gameOverPanel) {
        this.audioManager = audioManager;
        this.gameLoop = gameLoop;
        this.gameState = gameState;
        this.gameOverPanel = gameOverPanel;
    }

    /** Handles the game over event by stopping the game loop,
      updating the game state, displaying the game over panel,
      and managing audio playback.
    */
    public void showGameOver() {
        gameLoop.stop();
        gameState.setGameOver(true);
        if(gameOverPanel != null) {
            gameOverPanel.setVisible(true);
        }

        audioManager.stopBackgroundMusic();
        audioManager.playGameOverSound();
    }

    /** Resets the game state and game loop for a new game. */
    public void resetForNewGame() {
        gameLoop.stop();
        gameState.reset();

        if(gameOverPanel != null) {
            gameOverPanel.setVisible(false);
        }
    }
}
