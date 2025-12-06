package com.comp2042;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class RowsClearedEffectsHandler {

    private final AudioManagerInterface audioManager;
    private final GameStateInterface gameState;
    private final Label clearedRowsLabel;

    public RowsClearedEffectsHandler(AudioManagerInterface audioManager,
                                     GameStateInterface gameState,
                                     Label clearedRowsLabel) {
        this.audioManager = audioManager;
        this.gameState = gameState;
        this.clearedRowsLabel = clearedRowsLabel;
    }

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

    private void runOnUi(Runnable r) {
        if (Platform.isFxApplicationThread()) r.run();
        else Platform.runLater(r);
    }
}
