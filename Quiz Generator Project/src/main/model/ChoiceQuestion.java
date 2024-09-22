package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import persistence.Writable;

// Represents a choice question having a ID, description, a collection of choices and the corect choice
public class ChoiceQuestion {
    private String question;
    private List<Choice> choices;
    private char corretAnswer;
    private int questionId;

    // EFFECTS: construct a single question with the question
    // and its choices, following by the corret answer
    public ChoiceQuestion(int questionId, String question, List<Choice> choices, char corretAnswer) {
        this.questionId = 0;
        this.question = question;
        this.choices = choices;
        this.corretAnswer = corretAnswer;
    }

    // MODIFIES: this
    // EFFECTS: set the question to the given question
    public void setQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    // REQUIRE: there is only one choice can have status: true
    // MODIFIES: this
    // EFFECTS: set the answer to the given answer
    public void setCorrectAnswer(char correctAnswer) {
        this.corretAnswer = correctAnswer;
    }

    // // REQUIRE: there are no duplicated choices added in
    // // MODIFIES: this
    // // EFFECTS: add oneChoice onto the list of choices
    // public void addChoices(char symb, String des, boolean stat) {

    // if (choices.isEmpty()) {
    // choices.add(new Choice(symb, des, stat));
    // } else {
    // for (Choice c : choices) {
    // if (c != new Choice(symb, des, stat)) {
    // choices.add(new Choice(symb, des, stat));
    // }
    // }
    // }
    // }

    // // //MODIFIES: this
    // // //EFFECTS: remove oneChoice onto the list of choices
    // public void removeChoices(char symb) {
    // if (!this.choices.isEmpty()) {
    // for (Choice c : choices) {
    // if (c.getChoiceSymb() == symb) {
    // this.choices.remove(c);
    // }
    // }
    // }
    // }

    // // MODIFIES: this
    // // EFFECTS: return the list of choices
    // public List<Choice> getChoices() {
    // return choices;
    // }

    // MODIFIES: this
    // EFFECTS: return the questionID.
    public int getQuestionId() {
        this.questionId++;
        return questionId;
    }

    // MODIFIES: this
    // EFFECTS: return the question body.
    public String getQuestion() {
        return question;
    }

    // MODIFIES: this
    // EFFECTS: return the correct answer.
    public char getCorrectAnswer() {
        return corretAnswer;
    }

    // MODIFIES: this
    // EFFECTS: return true if the submitted answer macthes the correct answer.
    // otherwise, return false.
    public boolean checkAnswer(char answerSubmitted) {
        if (corretAnswer == answerSubmitted) {
            return true;
        }
        return false;
    }

    // REQUIRE: mark is either 0 or 1, no others.
    // EFFECT: if correct: mark = 1, if incorrect: mark = 0
    public int giveMark(char answerSubmitted) {
        int receivedMark = 0;

        if (checkAnswer(answerSubmitted)) {
            receivedMark = 1;
        }
        return receivedMark;
    }

    // @Override
    // public JSONObject toJson() {
    // JSONObject json = new JSONObject();
    // json.put("question ID", questionId);
    // json.put("question", question);
    // json.put("choices", choices);
    // json.put("correctAnswer", corretAnswer);

    // return json;
    // }
}
