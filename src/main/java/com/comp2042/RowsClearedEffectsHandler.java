package com.comp2042;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * Handles the effects of clearing rows in the Tetris game,
 * including playing sound effects, updating game state,
 * and refreshing the UI label that displays the number of cleared rows.
 */

public class RowsClearedEffectsHandler {

    private final AudioManagerInterface audioManager;
    private final GameStateInterface gameState;
    private final Label clearedRowsLabel;

    /** Constructs a RowsClearedEffectsHandler with the specified audio manager,
     * game state, and label for displaying cleared rows.
     *
     * @param audioManager    the audio manager to play sound effects
     * @param gameState       the game state to update cleared row count
     * @param clearedRowsLabel the label to display the number of cleared rows
     */
    public RowsClearedEffectsHandler(AudioManagerInterface audioManager,
                                     GameStateInterface gameState,
                                     Label clearedRowsLabel) {
        this.audioManager = audioManager;
        this.gameState = gameState;
        this.clearedRowsLabel = clearedRowsLabel;
    }

    /** Applies the effects of clearing rows, including playing sound,
     * updating game state, and refreshing the UI label.
     *
     * @param clearRow the ClearRow object containing information about cleared rows
     */
    public void apply(ClearRow clearRow) {
        if (clearRow == null || clearRow.getLinesRemoved() == 0)
            return;

        // Play sound
        audioManager.playLineClearSound();

        // Update state
        gameState.addClearedRows(clearRow.getLinesRemoved());

        // Update label on UI thread
        runOnUi(() ->
                clearedRowsLabel.setText(String.valueOf(gameState.getClearedRowCount()))
        );
    }

    /** Runs the specified Runnable on the JavaFX Application Thread.
     *
     * @param r the Runnable to execute
     */
    private void runOnUi(Runnable r) {
        if (Platform.isFxApplicationThread()) r.run();
        else Platform.runLater(r);
    }
}
