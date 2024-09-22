package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestShortAnswer {
    private ShortAnswerQuestion testShortAnswer;

    @BeforeEach
    void runBefore() {
        testShortAnswer = new ShortAnswerQuestion("1", "A?", "Yes");
    }

    @Test
    void testConstructor() {
        assertEquals("1", testShortAnswer.getQuestionId());
        assertEquals("A?", testShortAnswer.getQuestion());
        assertEquals("Yes", testShortAnswer.getAnswer());
    }

    @Test
    void testCheckAnswer() {
        assertTrue(testShortAnswer.checkAnswer("Yes"));
        assertFalse(testShortAnswer.checkAnswer("yes"));
        assertFalse(testShortAnswer.checkAnswer("No"));
    }

    @Test
    void testSetQuestionId() {
        testShortAnswer.setQuestionId("0");
        assertEquals("0", testShortAnswer.getQuestionId());

    }

    @Test
    void testSetQuestion() {
        testShortAnswer.setQuestion("B?");
        assertEquals("B?", testShortAnswer.getQuestion());

    }

    @Test
    void testSetAnswer() {
        testShortAnswer.setAnswer("b");
        assertEquals("b", testShortAnswer.getAnswer());

    }

    @Test
    void testGiveMark() {
        assertEquals(1, testShortAnswer.giveMark("Yes"));
        assertEquals(0, testShortAnswer.giveMark("yes"));
        assertEquals(0, testShortAnswer.giveMark("No"));
    }

    @Test
    void testToString() {
        assertEquals("1A?Yes", testShortAnswer.toString());
    }
}
