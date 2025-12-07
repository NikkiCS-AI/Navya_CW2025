package com.comp2042;

/**
 * Enum to represent the source of an event
 */

// checks who triggered the event, player or game engine
public enum EventSource {

    /** Event triggered by the user/player */
    USER,

    /** Event triggered by the game engine/thread */
    THREAD
}
