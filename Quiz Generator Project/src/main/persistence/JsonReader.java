package persistence;

import model.ShortAnswerQuestion;
import model.ShortAnswerQuiz;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads quiz from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShortAnswerQuiz read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ShortAnswerQuiz parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ShortAnswerQuiz saq = new ShortAnswerQuiz(name);
        addQuestions(saq, jsonObject);
        return saq;
    }

    // MODIFIES: saq
    // EFFECTS: parses questions from JSON object and adds them to workroom
    private void addQuestions(ShortAnswerQuiz saq, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("questions");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addQuestion(saq, nextQuestion);
        }
    }

    // MODIFIES: saq
    // EFFECTS: parses question from JSON object and adds it to workroom
    private void addQuestion(ShortAnswerQuiz saq, JSONObject jsonObject) {
        String questionId = jsonObject.getString("question ID");
        String question = jsonObject.getString("question");
        String answer = jsonObject.getString("answer");

        ShortAnswerQuestion sa = new ShortAnswerQuestion(questionId, question, answer);
        saq.addQuestion(sa);
    }
}
