package model;

import model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event event;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("Sensor open at door"); // (1)
        date = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Sensor open at door", event.toString());
    }

    @Test
    public void testEquals() {
        Event event2 = new Event("Sensor open at door");
        Event event4 = new Event("Sensor open at door");
        Event event3 = new Event("Another Event");
        Event eventNull = null;
        Object strEvent = "Sensor open at door";

        assertTrue(event2.equals(event4));
        assertFalse(event2.equals(event3));
        assertFalse(event2.equals(eventNull));
        assertFalse(event2.equals(strEvent));
    }

    @Test
    public void testhashCode() {
        Event event2 = new Event("Sensor open at door");
        Event event5 = new Event("Sensor open at door");

        assertEquals(event2.hashCode(), event5.hashCode());
    }

}
