package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a choice having a ID, description and answerstatus(True or False)
public class Choice {
    private char choiceSymb;
    private String description;
    private boolean answerstatus;

    // EFFECTS: creates an a single choice with choiecsymble, description and
    // answerstatus.
    public Choice(char choiceSymb, String description, boolean answerstatus) {
        this.choiceSymb = choiceSymb;
        this.description = description;
        this.answerstatus = answerstatus;
    }

    // EFFECTS: set the description to the provided one.
    public void setDesciption(String newDescription) {
        this.description = newDescription;
    }

    // EFFECTS: set the choice symble to the provided one.
    public void setChoiceSymb(char newChoiceSymb) {
        this.choiceSymb = newChoiceSymb;
    }

    // EFFECTS: set the choice state to the provided strate.
    public void setAnswerStatus(boolean newAnswerStatus) {
        this.answerstatus = newAnswerStatus;
    }

    // EFFECTS:return the choice description.
    public String getDescription() {
        return description;
    }

    // EFFECTS:return the choice symble.
    public char getChoiceSymb() {
        return choiceSymb;
    }

    // EFFECTS:return the choice status.
    public boolean getAnswerStatus() {
        return answerstatus;
    }

    // @Override
    // public JSONObject toJson() {
    // JSONObject json = new JSONObject();
    // json.put("choice symble", choiceSymb);
    // json.put("description", description);
    // json.put("answer status", answerstatus);
    // return json;
    // }
}
