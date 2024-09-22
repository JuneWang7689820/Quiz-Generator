package persistence;

import model.ShortAnswerQuestion;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkQuestion(String questionId, String question, String answer, ShortAnswerQuestion questions) {
        assertEquals(questionId, questions.getQuestionId());
        assertEquals(question, questions.getQuestion());
        assertEquals(answer, questions.getAnswer());
    }
}
