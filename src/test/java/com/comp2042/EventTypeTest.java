package com.comp2042;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EventTypeTest {

    @Test
    void name() {
        assertEquals("DOWN", EventType.DOWN.name());
    }

    @Test
    void ordinal() {
        assertEquals(0, EventType.DOWN.ordinal());
    }

    @Test
    void testToString() {
        assertEquals("DOWN", EventType.DOWN.toString());
    }

    @Test
    void testEquals() {
        assertEquals(EventType.DOWN, EventType.DOWN);
    }

    @Test
    void testHashCode() {
        assertEquals(EventType.DOWN.hashCode(), EventType.DOWN.hashCode());
    }

    //@Test
    //void testClone() {
    //assertThrows(CloneNotSupportedException.class, () -> {
    // EventType.DOWN.clone();
    // });
    // }

    @Test
    void compareTo() {
        assertTrue(EventType.DOWN.compareTo(EventType.LEFT) < 0);
    }

    @Test
    void getDeclaringClass() {
        assertEquals(EventType.class, EventType.DOWN.getDeclaringClass());
    }

    @Test
    void describeConstable() {
        assertTrue(EventType.DOWN.describeConstable().isPresent());
    }

    @Test
    void valueOf() {
        assertEquals(EventType.DOWN, EventType.valueOf("DOWN"));
    }

    @Test
    void testFinalize() {
        // finalize is protected and cannot be called directly; this test is just a placeholder
        assertTrue(true);
    }

    @Test
    void values() {
        EventType[] eventTypes = EventType.values();
        assertEquals(4, eventTypes.length);
        assertEquals(EventType.DOWN, eventTypes[0]);
        assertEquals(EventType.LEFT, eventTypes[1]);
        assertEquals(EventType.RIGHT, eventTypes[2]);
        assertEquals(EventType.ROTATE, eventTypes[3]);
    }

    @Test
    void testValueOf() {
        assertEquals(EventType.LEFT, EventType.valueOf("LEFT"));
    }
}