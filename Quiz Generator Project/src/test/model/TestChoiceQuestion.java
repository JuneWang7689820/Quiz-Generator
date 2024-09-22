package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestChoiceQuestion {
    private ChoiceQuestion testQuestion;
    private List<Choice> choiceList;
    // private Choice testChoice1;
    // private Choice testChoice2;

    @BeforeEach
    void runBefore() {
        // testChoice1 = new Choice('a', "A", true);
        // testChoice2 = new Choice('b', "B", false);
        choiceList = new ArrayList<>();
        testQuestion = new ChoiceQuestion(0, "A?", choiceList, 'a');
    }

    @Test
    void testConstructor() {

        assertEquals(0, choiceList.size());
        assertEquals(1, testQuestion.getQuestionId());
        assertEquals("A?", testQuestion.getQuestion());
        assertEquals('a', testQuestion.getCorrectAnswer());
    }

    @Test
    void testSetQuestion() {
        testQuestion.setQuestion("B?");
        assertEquals("B?", testQuestion.getQuestion());

    }

    @Test
    void testSetAnswer() {
        testQuestion.setCorrectAnswer('b');
        assertEquals('b', testQuestion.getCorrectAnswer());

    }

    // @Test
    // void testAddandRemoveChoices() {

    // //add choice 1
    // testQuestion.addChoices(testChoice1.getChoiceSymb(),
    // testChoice1.getDescription(), testChoice1.getAnswerStatus());
    // assertEquals(1, choiceList.size());
    // //add choice 2
    // testQuestion.addChoices(testChoice2.getChoiceSymb(),
    // testChoice2.getDescription(), testChoice2.getAnswerStatus());
    // assertEquals(2, choiceList.size());
    // //add choice 1 again
    // testQuestion.addChoices(testChoice1.getChoiceSymb(),
    // testChoice1.getDescription(), testChoice1.getAnswerStatus());
    // assertEquals(2, choiceList.size());
    // assertEquals(testChoice1, choiceList.get(0));
    // assertEquals(testChoice2, choiceList.get(1));

    // //remove choice 1
    // testQuestion.removeChoices(testChoice1.getChoiceSymb());
    // assertEquals(1, choiceList.size());
    // assertEquals(testChoice2, choiceList.get(0));
    // //remove choice 2
    // testQuestion.removeChoices(testChoice2.getChoiceSymb());
    // assertEquals(0, choiceList.size());
    // }

    @Test
    void testCheckAnswer() {
        assertTrue(testQuestion.checkAnswer('a'));
        assertFalse(testQuestion.checkAnswer('b'));
        assertFalse(testQuestion.checkAnswer('c'));
    }

    @Test
    void testGiveMark() {
        assertEquals(1, testQuestion.giveMark('a'));
        assertEquals(0, testQuestion.giveMark('b'));
        assertEquals(0, testQuestion.giveMark('c'));
    }
}
