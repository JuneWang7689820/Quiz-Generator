package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestShortAnswerQuiz {
    private ShortAnswerQuestion testShortAnswer;
    private ShortAnswerQuiz testquiz;

    @BeforeEach
    void runBefore() {
        testShortAnswer = new ShortAnswerQuestion("1", "A?", "Yes");
        testquiz = new ShortAnswerQuiz("myquiz");
    }

    @Test
    void test() {
        Iterator<ShortAnswerQuestion> iterator = testquiz.iterator();
        assertTrue(testquiz.isEmpty());
        assertFalse(iterator.hasNext());
        assertEquals(0, testquiz.size());

        testquiz.addQuestion(testShortAnswer);
        assertFalse(testquiz.isEmpty());
        assertTrue(iterator.hasNext());
        assertEquals(testShortAnswer, testquiz.get(0));
        assertEquals(1, testquiz.size());

    }

    @Test
    void testRemove() {
        assertFalse(testquiz.removeQuestionById("1"));

        Iterator<ShortAnswerQuestion> iterator = testquiz.iterator();
        assertTrue(testquiz.isEmpty());
        assertFalse(iterator.hasNext());
        assertEquals(0, testquiz.size());

        testquiz.addQuestion(testShortAnswer);
        assertFalse(testquiz.removeQuestionById("2"));
        assertTrue(testquiz.removeQuestionById("1"));

        assertTrue(testquiz.isEmpty());
        assertFalse(iterator.hasNext());
        assertEquals(0, testquiz.size());
    }

    @Test
    void testView() {
        Iterator<ShortAnswerQuestion> iterator = testquiz.iterator();
        assertTrue(testquiz.isEmpty());
        assertFalse(iterator.hasNext());
        assertEquals(0, testquiz.size());

        testquiz.addQuestion(testShortAnswer);

        List<String> questionViews = testquiz.viewQuestions();

        assertFalse(questionViews.isEmpty());
        assertEquals(1, questionViews.size());
    }
}
