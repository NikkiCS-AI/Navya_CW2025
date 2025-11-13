package com.comp2042;

import com.comp2042.logic.bricks.Brick;
import com.comp2042.logic.bricks.BrickGenerator;

public class HoldBrick {
    private Brick heldBrick;        // The brick currently being held
    private boolean isHeld;
    private final BrickGenerator generator;

    public HoldBrick(BrickGenerator generator) {
        this.generator = generator;
        this.heldBrick = null;
        this.isHeld = false;
    }

    /**
     * Handles the hold or swap logic.
     * @param currentBrick The brick currently active on the board.
     * @return The brick that should now be active (either new or swapped).
     */
    public Brick hold(Brick currentBrick) {
        if (isHeld) {
            // You can only hold once per active piece
            return currentBrick;
        }

        Brick temp = heldBrick;
        if (heldBrick == null) {
            // First time: save the current brick and generate a new one
            heldBrick = currentBrick;
            currentBrick = generator.getNextBrick();
        } else {
            // Swap the current and held bricks
            heldBrick = currentBrick;
            currentBrick = temp;
        }

        isHeld = true;
        return currentBrick;
    }

    /** Resets hold availability after a brick locks down. */
    public void resetHoldAvailability() {
        isHeld = false;
    }

    /** @return the held brick (for display) */
    public Brick getHeldBrick() {
        return heldBrick;
    }

    /** @return true if player already used hold this turn */
    public boolean hasHeldThisTurn() {
        return isHeld;
    }
}
