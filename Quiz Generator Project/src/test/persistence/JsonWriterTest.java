package persistence;

import model.ShortAnswerQuestion;
import model.ShortAnswerQuiz;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    // NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter
    // is to
    // write data to a file and then use the reader to read it back in and check
    // that we
    // read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ShortAnswerQuiz wr = new ShortAnswerQuiz("My quiz");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyShortQuiz() {
        try {
            ShortAnswerQuiz wr = new ShortAnswerQuiz("My quiz");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShortQuiz.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShortQuiz.json");
            wr = reader.read();
            assertEquals("My quiz", wr.getName());
            assertEquals(0, wr.numQuestions());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShortQuiz() {
        try {
            ShortAnswerQuiz wr = new ShortAnswerQuiz("My quiz");
            wr.addQuestion(new ShortAnswerQuestion("1", "A", "a"));
            wr.addQuestion(new ShortAnswerQuestion("2", "B", "b"));
            JsonWriter writer = new JsonWriter("./data/testWriterShortQuiz.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterShortQuiz.json");
            wr = reader.read();
            assertEquals("My quiz", wr.getName());
            List<ShortAnswerQuestion> questions = wr.getQuestion();
            assertEquals(2, questions.size());
            checkQuestion("1", "A", "a", questions.get(0));
            checkQuestion("2", "B", "b", questions.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}