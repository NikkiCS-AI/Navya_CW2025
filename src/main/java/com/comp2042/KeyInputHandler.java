 package com.comp2042;
import com.comp2042.GameMovementInterface;
import javafx.scene.input.KeyCode;

/**
 * The {@code KeyInputHandler} class processes keyboard inputs
 * and translates them into game movement commands by interacting
 * with the {@link GameMovementInterface}.
 */

public class KeyInputHandler {
    private final GameMovementInterface gameMovement;

    /** Constructor to initialize the KeyInputHandler with a GameMovementInterface */
    public KeyInputHandler(GameMovementInterface gameMovement) {
        this.gameMovement = gameMovement;
    }

    /** Method to handle movement-related key inputs */
    public void handleMovementKey(KeyCode code) {
        switch (code) {
            case LEFT:
                case A:
                    gameMovement.moveLeft(); break;
            case RIGHT:
                case D:
                    gameMovement.moveRight(); break;
            case UP:
                case W:
                    gameMovement.rotate(); break;
            case DOWN:
                case S:
                    gameMovement.softDrop(); break;

            case SPACE:
                gameMovement.hardDrop(); break;
        }
    }

    /** Method to handle system-related key inputs */
    public void handleSystemKey(KeyCode code){
        switch(code){
            case N: gameMovement.newGame(); break;
            case C: gameMovement.hold(); break;
        }
    }
}
