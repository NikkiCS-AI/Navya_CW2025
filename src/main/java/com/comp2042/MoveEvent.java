package com.comp2042;

/**
 * The MoveEvent class represents an event that occurs during the game,
 * such as moving a shape, rotating it, or dropping it. It encapsulates
 * the type of event and its source.
 */

public final class MoveEvent {
    private final EventType eventType;
    private final EventSource eventSource;

    /** creates new event */
    public MoveEvent(EventType eventType, EventSource eventSource) {
        this.eventType = eventType;
        this.eventSource = eventSource;
    }

    /** used by game logic to determine what type of event it is */
    public EventType getEventType() {
        return eventType;
    }

    /** used by game logic to determine who caused the event */
    public EventSource getEventSource() {
        return eventSource;
    }
}
