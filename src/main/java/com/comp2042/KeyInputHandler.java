package com.comp2042;

import javafx.scene.input.KeyCode;

public class KeyInputHandler {

    private final GuiController gui;

    public KeyInputHandler(GuiController gui) {
        this.gui = gui;
    }

    public void handleMovementKey(KeyCode code) {

        // FIX: use GuiController interface, not fields
        if (gui.isPaused() || gui.isGameOver()) return;

        switch (code) {
            case LEFT:
            case A:
                gui.refreshBrick(gui.eventListener.onLeftEvent(
                        new MoveEvent(EventType.LEFT, EventSource.USER)
                ));
                break;

            case RIGHT:
            case D:
                gui.refreshBrick(gui.eventListener.onRightEvent(
                        new MoveEvent(EventType.RIGHT, EventSource.USER)
                ));
                break;

            case UP:
            case W:
                gui.refreshBrick(gui.eventListener.onRotateEvent(
                        new MoveEvent(EventType.ROTATE, EventSource.USER)
                ));
                break;

            case DOWN:
            case S:
                gui.performMoveDown(new MoveEvent(EventType.DOWN, EventSource.USER));
                break;

            default:
                break;
        }
    }

    public void handleSystemKey(KeyCode code) {
        switch (code) {

            case N:
                gui.newGame(null);
                break;

            case C:
                gui.eventListener.onHoldEvent();
                break;

            default:
                break;
        }
    }
}
