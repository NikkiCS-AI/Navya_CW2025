package com.comp2042.logic.bricks;

import java.util.List;

/**
 * Enum representing the different types of Tetromino shapes
 * along with their rotation states.
 */

public enum TetrominoShapeType {

    /** I shape tetromino with its rotation states. */
    I(List.of(
            (new int[][]{
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }),
            (new int[][]{
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}
            })
    )),

    /** J shape tetromino with its rotation states. */
    J(List.of(
            (new int[][]{
                    {0, 0, 0, 0},
                    {2, 2, 2, 0},
                    {0, 0, 2, 0},
                    {0, 0, 0, 0}
            }),
            (new int[][]{
                    {0, 0, 0, 0},
                    {0, 2, 2, 0},
                    {0, 2, 0, 0},
                    {0, 2, 0, 0}
            }),
            (new int[][]{
                    {0, 0, 0, 0},
                    {0, 2, 0, 0},
                    {0, 2, 2, 2},
                    {0, 0, 0, 0}
            }),
            (new int[][]{
                    {0, 0, 2, 0},
                    {0, 0, 2, 0},
                    {0, 2, 2, 0},
                    {0, 0, 0, 0}
            })
    )),

    /** L shape tetromino with its rotation states. */
    L(List.of(
            (new int[][]{
                    {0, 0, 0, 0},
                    {0, 3, 3, 3},
                    {0, 3, 0, 0},
                    {0, 0, 0, 0}
            }),
            (new int[][]{
                    {0, 0, 0, 0},
                    {0, 3, 3, 0},
                    {0, 0, 3, 0},
                    {0, 0, 3, 0}
            }),
            (new int[][]{
                    {0, 0, 0, 0},
                    {0, 0, 3, 0},
                    {3, 3, 3, 0},
                    {0, 0, 0, 0}
            }),
            (new int[][]{
                    {0, 3, 0, 0},
                    {0, 3, 0, 0},
                    {0, 3, 3, 0},
                    {0, 0, 0, 0}
            })
    )),

    /** O shape tetromino with its rotation states. */
    O(List.of(
            new int[][]{
                    {0, 0, 0, 0},
                    {0, 4, 4, 0},
                    {0, 4, 4, 0},
                    {0, 0, 0, 0}
            },
            new int[][]{
                    {0, 0, 0, 0},
                    {0, 4, 4, 0},
                    {0, 4, 4, 0},
                    {0, 0, 0, 0}
            }
    )),


    /** S shape tetromino with its rotation states. */
    S(List.of(
            (new int[][]{
                    {0, 0, 0, 0},
                    {0, 5, 5, 0},
                    {5, 5, 0, 0},
                    {0, 0, 0, 0}
            }),
            (new int[][]{
                    {5, 0, 0, 0},
                    {5, 5, 0, 0},
                    {0, 5, 0, 0},
                    {0, 0, 0, 0}
            })
    )),

    /** T shape tetromino with its rotation states. */
    T(List.of(
            (new int[][]{
                    {0, 0, 0, 0},
                    {6, 6, 6, 0},
                    {0, 6, 0, 0},
                    {0, 0, 0, 0}
            }),
            (new int[][]{
                    {0, 6, 0, 0},
                    {0, 6, 6, 0},
                    {0, 6, 0, 0},
                    {0, 0, 0, 0}
            }),
            (new int[][]{
                    {0, 6, 0, 0},
                    {6, 6, 6, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }),
            (new int[][]{
                    {0, 6, 0, 0},
                    {6, 6, 0, 0},
                    {0, 6, 0, 0},
                    {0, 0, 0, 0}
            })
    )),

    /** Z shape tetromino with its rotation states. */
    Z(List.of(
            (new int[][]{
                    {0, 0, 0, 0},
                    {7, 7, 0, 0},
                    {0, 7, 7, 0},
                    {0, 0, 0, 0}
            }),
            (new int[][]{
                    {0, 7, 0, 0},
                    {7, 7, 0, 0},
                    {7, 0, 0, 0},
                    {0, 0, 0, 0}
            })
    ));

    /** List of rotation states for the tetromino shape. */
    private final List<int[][]> rotations;

    /**
     * Constructor for TetrominoShapeType enum.
     *
     * @param rotations List of rotation states for the tetromino shape.
     */
    TetrominoShapeType(List<int[][]> rotations) {
        this.rotations = rotations;
    }

    /**
     * Get the list of rotation states for the tetromino shape.
     *
     * @return List of 2D arrays representing rotation states.
     */
    public List<int[][]> getRotations() {
        return rotations;
    }
}
