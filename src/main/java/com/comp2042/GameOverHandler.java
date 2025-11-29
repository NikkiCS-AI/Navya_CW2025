package com.comp2042;

public class GameOverHandler {
    private final AudioManagerInterface audioManager;
    private final GameLoopInterface gameLoop;
    private final GameStateInterface gameState;
    private final GameOverPanel gameOverPanel;

    public GameOverHandler(AudioManagerInterface audioManager,
                           GameLoopInterface gameLoop,
                           GameStateInterface gameState,
                           GameOverPanel gameOverPanel) {
        this.audioManager = audioManager;
        this.gameLoop = gameLoop;
        this.gameState = gameState;
        this.gameOverPanel = gameOverPanel;
    }

    public void showGameOver() {
        gameLoop.stop();
        gameState.setGameOver(true);
        if(gameOverPanel != null) {
            gameOverPanel.setVisible(true);
        }

        audioManager.stopBackgroundMusic();
        audioManager.playGameOverSound();
    }

    public void resetForNewGame() {
        gameLoop.stop();
        gameState.reset();

        if(gameOverPanel != null) {
            gameOverPanel.setVisible(false);
        }
    }
}
