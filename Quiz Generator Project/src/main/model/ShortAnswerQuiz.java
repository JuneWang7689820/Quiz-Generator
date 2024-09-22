package model;

import persistence.Writable;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Represents a shortanswerquiz having a collection of shortanswer questions
public class ShortAnswerQuiz implements Writable {
    private String name;
    private List<ShortAnswerQuestion> shortanswers;

    // EFFECTS: constructs quiz with a name and empty list of questions
    public ShortAnswerQuiz(String name) {
        this.name = name;
        shortanswers = new ArrayList<>();
    }

    // EFFECTS: return the name of the quiz
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds questions to this quiz
    public void addQuestion(ShortAnswerQuestion sq) {
        EventLog.getInstance().logEvent(
                new Event("Added questions: " + sq.getQuestionId() + ": " + sq.getQuestion() + " " + sq.getAnswer()));
        shortanswers.add(sq);
    }

    // EFFECTS: returns an unmodifiable list of questions in this quiz
    public List<ShortAnswerQuestion> getQuestion() {
        return Collections.unmodifiableList(shortanswers);
    }

    // EFFECTS: returns number of questions in this quiz
    public int numQuestions() {
        return shortanswers.size();
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of shortanswerquiz to file
    // alone with a name and the questions.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("questions", quesToJson());
        return json;
    }

    // EFFECTS: returns questions in this quiz as a JSON array
    private JSONArray quesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ShortAnswerQuestion sq : shortanswers) {
            jsonArray.put(sq.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns true if the list is empty
    // otherwise, return false;
    public boolean isEmpty() {
        return shortanswers.isEmpty();
    }

    // EFFECTS: iterator
    public Iterator<ShortAnswerQuestion> iterator() {
        return shortanswers.iterator();
    }

    // EFFECTS: getting the item corresponded with the index
    public ShortAnswerQuestion get(int index) {
        return shortanswers.get(index);
    }

    // EFFECTS: return the size of the quiz list
    public int size() {
        return shortanswers.size();
    }

    // EFFECTS: Remove a question by ID, return true if successfully found and
    // remove,
    // false if not found or no questions in the list.
    public boolean removeQuestionById(String questionId) {
        Iterator<ShortAnswerQuestion> iterator = shortanswers.iterator();
        while (iterator.hasNext()) {
            ShortAnswerQuestion question = iterator.next();
            if (question.getQuestionId().equals(questionId)) {
                iterator.remove();
                EventLog.getInstance().logEvent(new Event("Remove question by ID: " + questionId));
                return true;
            }
        }
        return false;
    }

    // EFFECTS: View questions, typically returns a list of questions
    public List<String> viewQuestions() {
        List<String> questionViews = new ArrayList<>();
        for (ShortAnswerQuestion question : shortanswers) {
            questionViews.add("ID: " + question.getQuestionId() + " | Q: " + question.getQuestion() + " | A: "
                    + question.getAnswer());
        }
        EventLog.getInstance().logEvent(new Event("View Questions"));
        return questionViews;
    }
}
