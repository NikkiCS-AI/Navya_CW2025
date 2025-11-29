 package com.comp2042;
import com.comp2042.GameMovementInterface;
import javafx.scene.input.KeyCode;

public class KeyInputHandler {
    private final GameMovementInterface gameMovement;
    public KeyInputHandler(GameMovementInterface gameMovement) {
        this.gameMovement = gameMovement;
    }

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
        }
    }
    public void handleSystemKey(KeyCode code){
        switch(code){
            case N: gameMovement.newGame(); break;
            case C: gameMovement.hold(); break;
        }
    }
}
