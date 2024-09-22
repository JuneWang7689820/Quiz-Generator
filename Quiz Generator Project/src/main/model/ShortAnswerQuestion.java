package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a shortanswer question having a collection of shortanswer questions
public class ShortAnswerQuestion implements Writable {
    private String question;
    private String answer;
    private String questionId;

    // EFFECTS: construct a single question with the question
    // and its correct answer
    public ShortAnswerQuestion(String questionId, String question, String answer) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
    }

    // MODIFIES: this
    // EFFECTS: set the answer to the given answer
    public void setQuestionId(String newId) {
        EventLog.getInstance().logEvent(new Event("QuestionID set"));
        this.questionId = newId;
    }

    // EFFECTS: set the question to the given question
    public void setQuestion(String newQuestion) {
        EventLog.getInstance().logEvent(new Event("Question set"));
        this.question = newQuestion;
    }

    // MODIFIES: this
    // EFFECTS: set the answer to the given answer
    public void setAnswer(String newAnswer) {
        EventLog.getInstance().logEvent(new Event("Answer set"));

        this.answer = newAnswer;
    }

    // MODIFIES: this
    // EFFECTS: return the question ID
    public String getQuestionId() {
        return questionId;
    }

    // MODIFIES: this
    // EFFECTS: return the question body
    public String getQuestion() {
        return question;
    }

    // MODIFIES: this
    // EFFECTS: return the correct answer
    public String getAnswer() {
        return answer;
    }

    // EFFECT: return whether the answer is corret;
    public boolean checkAnswer(String answerSubmitted) {
        return answer.equals(answerSubmitted);
    }

    // EFFECT: if correct: mark = 1, if incorrect: mark = 0
    public int giveMark(String answerSubmitted) {
        int receivedMark = 0;

        if (checkAnswer(answerSubmitted)) {
            receivedMark = 1;
        }
        return receivedMark;
    }

    // EFFECTS: returns string representation of this question
    public String toString() {
        return questionId + question + answer;
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of shortanswerquestion to file
    // alone with the question ID, question and the answer
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question ID", questionId);
        json.put("question", question);
        json.put("answer", answer);
        return json;
    }
}