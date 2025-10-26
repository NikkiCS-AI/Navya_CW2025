package com.comp2042;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MoveEventTest {

    @Test
    void getEventType() {
        assertDoesNotThrow(
                () -> {
                    MoveEvent event = new MoveEvent(EventType.DOWN, EventSource.USER);
                    assertEquals(EventType.DOWN, event.getEventType());
                }
        );
    }

    @Test
    void getEventSource() {
        assertDoesNotThrow(
                () -> {
                    MoveEvent event = new MoveEvent(EventType.DOWN, EventSource.USER);
                    assertEquals(EventSource.USER, event.getEventSource());
                }
        );
    }
}