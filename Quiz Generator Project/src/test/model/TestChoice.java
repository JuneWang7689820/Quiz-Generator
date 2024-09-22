package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestChoice {
    private Choice testChoice1;
    private Choice testChoice2;

    @BeforeEach
    void runBefore() {
        testChoice1 = new Choice('a', "A", true);
        testChoice2 = new Choice('b', "B", false);
    }

    @Test
    void testConstructor() {
        assertEquals('a', testChoice1.getChoiceSymb());
        assertEquals("A", testChoice1.getDescription());
        assertTrue(testChoice1.getAnswerStatus());

        assertEquals('b', testChoice2.getChoiceSymb());
        assertEquals("B", testChoice2.getDescription());
        assertFalse(testChoice2.getAnswerStatus());
    }

    @Test
    void testSetChioce() {
        testChoice1.setDesciption("C");
        assertEquals("C", testChoice1.getDescription());
        testChoice1.setDesciption("D");
        testChoice1.setDesciption("E");
        assertEquals("E", testChoice1.getDescription());

        testChoice1.setChoiceSymb('c');
        assertEquals('c', testChoice1.getChoiceSymb());
        testChoice1.setChoiceSymb('b');
        assertEquals('b', testChoice1.getChoiceSymb());

        testChoice1.setAnswerStatus(false);
        assertFalse(testChoice1.getAnswerStatus());
        testChoice1.setAnswerStatus(true);
        assertTrue(testChoice1.getAnswerStatus());

        testChoice1.setAnswerStatus(false);
        testChoice1.setAnswerStatus(false);
        assertFalse(testChoice1.getAnswerStatus());
    }

}
