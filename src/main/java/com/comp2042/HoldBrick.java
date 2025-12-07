package com.comp2042;

import com.comp2042.logic.bricks.Brick;
import com.comp2042.logic.bricks.BrickGenerator;

/**
 * Manages the hold brick functionality in Tetris.
 */

public class HoldBrick {
    private Brick heldBrick;        /** The brick currently being held */
    private boolean isHeld;         /** True if hold was used on this drop */
    private final BrickGenerator generator;

    /** Constructor initializing with a brick generator. */
    public HoldBrick(BrickGenerator generator) {
        this.generator = generator;
        this.heldBrick = null;
        this.isHeld = false;
    }

    /**
     * Classic Tetris hold mechanic.
     * @param currentBrick The brick currently falling.
     * @return The brick that should become active (swapped or newly generated).
     */
    public Brick hold(Brick currentBrick) {
        if (isHeld) {
            // Holding twice per drop is NOT allowed
            return currentBrick;
        }

        Brick temp = heldBrick;

        // First time holding: store current and get a fresh brick
        if (heldBrick == null) {
            heldBrick = currentBrick;
            currentBrick = generator.getBrick();   // ✔ get a NEW piece
        }
        else {
            // Classic swap
            heldBrick = currentBrick;
            currentBrick = temp;
        }

        isHeld = true;
        return currentBrick;
    }

    /** Reset after a brick locks down (drop completes). */
    public void resetHoldAvailability() {
        isHeld = false;
    }

    /** Returns the stored brick (for GUI display). */
    public Brick getHeldBrick() {
        return heldBrick;
    }

    /** True if the player already held once this drop. */
    public boolean hasHeldThisTurn() {
        return isHeld;
    }
}
