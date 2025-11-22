package com.comp2042;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInputHandler {

    private final GuiController gui;

    public KeyInputHandler(GuiController gui) {
        this.gui = gui;
    }
    // Set up key event handler
    public void handleMovementKey(KeyCode code) {
        if (gui.isPause.get() || gui.isGameOver.get()) return;

        // movement switch will go here
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
                // not a movement key
                break;
        }


    }

    public void handleSystemKey(KeyCode code) {
        // system actions (new game, hold)
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



