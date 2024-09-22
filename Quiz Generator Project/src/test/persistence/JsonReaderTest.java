package persistence;

import model.ShortAnswerQuestion;
import model.ShortAnswerQuiz;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ShortAnswerQuiz wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyShortQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyShortQuiz.json");
        try {
            ShortAnswerQuiz wr = reader.read();
            assertEquals("My quiz", wr.getName());
            assertEquals(0, wr.numQuestions());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralShortQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralShortQuiz.json");
        try {
            ShortAnswerQuiz wr = reader.read();
            assertEquals("My quiz", wr.getName());
            List<ShortAnswerQuestion> questions = wr.getQuestion();
            assertEquals(2, questions.size());
            checkQuestion("1", "A", "a", questions.get(0));
            checkQuestion("2", "B", "b", questions.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}