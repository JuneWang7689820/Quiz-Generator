package ui;

import model.EventLog;
import model.Event;
import model.ShortAnswerQuestion;
import model.ShortAnswerQuiz;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// A quiz application that allows the user to add and review their questions
public class ShortAnswerQuizReviewerGUI extends JFrame implements WindowListener {
    private ShortAnswerQuiz quiz;
    private int currentIndex = 0;

    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private DefaultListModel<String> listModel;
    // private List<String> listModel;
    private JList<String> questionList;
    private JTextField questionIdField;
    private JTextField questionField;
    private JTextField answerField;
    private JFrame frame;

    // EFFECTS: creates an instance of the QuizReviewer application
    public ShortAnswerQuizReviewerGUI() {
        init();
        windowSetUp();
        // Create control panel
        JPanel controlPanel = new JPanel();
        frame = new JFrame();
        controlPanel.setLayout(new GridLayout(8, 1));
        this.addWindowListener(this);

        JButton addButton = new JButton("Add Question");
        JButton removeButton = new JButton("Remove Question");
        JButton saveButton = new JButton("Save Questions");
        JButton loadButton = new JButton("Load Questions");
        JButton viewButton = new JButton("View Questions");
        JButton quitButton = new JButton("Quit");

        questionIdField = new JTextField("Enter Question ID");
        questionField = new JTextField("Enter Question");
        answerField = new JTextField("Enter Answer");

        addingButtons(controlPanel, addButton, removeButton, saveButton, loadButton, viewButton, quitButton);
        add(controlPanel, BorderLayout.WEST);

        // Create list to display questions
        creatList();
        specifyEachButton(addButton, removeButton, saveButton, loadButton, viewButton, quitButton);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the application with the starting values
    private void init() {
        this.quiz = new ShortAnswerQuiz("My Quiz");
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: relate the function to each button.
    @SuppressWarnings("methodlength")
    private void specifyEachButton(JButton addButton, JButton removeButton, JButton saveButton, JButton loadButton,
            JButton viewButton, JButton quitButton) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewQuestion();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeQuestions();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveQuestions();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadQuestions();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewQuestions();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitApplication();
            }
        });
    }

    // EFFECTS: Set up the text/font appears on the window.
    private void windowSetUp() {
        setTitle("Short Answer Quiz Generator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    // EFFECTS: Adding buttons to the panel.
    private void addingButtons(JPanel controlPanel, JButton addButton, JButton removeButton, JButton saveButton,
            JButton loadButton, JButton viewButton, JButton quitButton) {
        controlPanel.add(questionIdField);
        controlPanel.add(questionField);
        controlPanel.add(answerField);
        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(saveButton);
        controlPanel.add(loadButton);
        controlPanel.add(viewButton);
        controlPanel.add(quitButton);
    }

    // EFFECTS: Creating a default list to track the behavious of the elements in
    // the list
    private void creatList() {
        listModel = new DefaultListModel<>();
        questionList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(questionList);
        add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds a quetion to the quiz
    private void addNewQuestion() {
        String questionId = questionIdField.getText();
        String question = questionField.getText();
        String answer = answerField.getText();

        if (!questionId.isEmpty() && !question.isEmpty() && !answer.isEmpty()) {
            ShortAnswerQuestion shortAnswerQuestion = new ShortAnswerQuestion(questionId, question, answer);
            quiz.addQuestion(shortAnswerQuestion);
            listModel.addElement("Question " + questionId + ":" + question);
            listModel.addElement("Answer: " + answer);
            clearTextFields();
            frame.repaint();
            JOptionPane.showMessageDialog(this, "New Question successfully created!");
        } else {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
        }
    }

    // EFFECTS: return true if the list has the next element, false otherwise
    private boolean tryHasNext(String questionId, boolean found, Iterator<ShortAnswerQuestion> iterator) {
        while (iterator.hasNext()) {
            ShortAnswerQuestion s = iterator.next();
            if (s.getQuestionId().equals(questionId)) {
                iterator.remove();
                found = true;
                break;
            }
        }
        return found;
    }

    // EFFECTS: save questions to the file
    private void saveQuestions() {
        try {
            jsonWriter.open();
            jsonWriter.write(quiz);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Questions saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: load all questions being saved from the file
    private void loadQuestions() {
        try {
            quiz = jsonReader.read();
            listModel.clear();
            for (ShortAnswerQuestion question : quiz.getQuestion()) {
                listModel.addElement("Question " + question.getQuestionId() + ":" + question.getQuestion());
                listModel.addElement("Answer: " + question.getAnswer());
            }
            JOptionPane.showMessageDialog(this, "Questions loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: displays all questions, one at a time
    private void viewQuestions() {
        List<String> questions = quiz.viewQuestions();
        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No questions to display!", "View Questions",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JDialog viewDialog = getViewDialog();
        JPanel questionPanel = new JPanel();
        JTextArea questionArea = new JTextArea(5, 30);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(false);
        questionPanel.add(questionArea);

        setViewButtons(viewDialog, questionPanel, questionArea);
    }

    // MODIFIES: this
    // EFFECTS: remove a quetion from the quiz
    private void removeQuestions() {
        String questionId = JOptionPane.showInputDialog(this, "Enter the Question ID to remove:");
        if (questionId != null && !questionId.trim().isEmpty()) {
            boolean found = quiz.removeQuestionById(questionId);
            if (found) {
                // Update the list model to reflect the removed question
                for (int i = 0; i < listModel.size(); i++) {
                    if (listModel.get(i).contains("ID: " + questionId)) {
                        listModel.removeElementAt(i);
                        frame.repaint();
                        break;
                    }
                }
                JOptionPane.showMessageDialog(this, "Question successfully removed!");
            } else {
                JOptionPane.showMessageDialog(this, "No such question found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid Question ID.");
        }
    }

    // EFFECTS: set up the window for the view menu: display questions without the
    // answer
    // set up the buttons appeared on the screen:
    // see prevous question, see next question, show answer
    private void setViewButtons(JDialog viewDialog, JPanel questionPanel, JTextArea questionArea) {
        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton showAnswerButton = new JButton("Show Answer");
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(showAnswerButton);
        viewDialog.add(questionPanel, BorderLayout.CENTER);
        viewDialog.add(buttonPanel, BorderLayout.SOUTH);
        final int[] currentIndex = { 0 };
        updateQuestionDisplay(questionArea, currentIndex[0]);
        viewPrevious(viewDialog, questionArea, prevButton, currentIndex);
        viewNext(viewDialog, questionArea, nextButton, currentIndex);
        showAnswer(viewDialog, showAnswerButton, currentIndex);
        viewDialog.setVisible(true);
    }

    // EFFECTS: display the answer of the questions
    private void showAnswer(JDialog viewDialog, JButton showAnswerButton, final int[] currentIndex) {
        showAnswerButton.addActionListener(e -> {
            ShortAnswerQuestion currentQuestion = quiz.get(currentIndex[0]);
            JOptionPane.showMessageDialog(viewDialog, "Answer: " + currentQuestion.getAnswer(), "Answer",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    // EFFECTS: display the next question
    private void viewNext(JDialog viewDialog, JTextArea questionArea, JButton nextButton, final int[] currentIndex) {
        nextButton.addActionListener(e -> {
            if (currentIndex[0] < quiz.size() - 1) {
                currentIndex[0]++;
                updateQuestionDisplay(questionArea, currentIndex[0]);
            } else {
                JOptionPane.showMessageDialog(viewDialog, "No more questions!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // EFFECTS: display the previous question
    private void viewPrevious(JDialog viewDialog, JTextArea questionArea, JButton prevButton,
            final int[] currentIndex) {
        prevButton.addActionListener(e -> {
            if (currentIndex[0] > 0) {
                currentIndex[0]--;
                updateQuestionDisplay(questionArea, currentIndex[0]);
            } else {
                JOptionPane.showMessageDialog(viewDialog, "No previous question!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // EFFECTS: display the view dialog
    private JDialog getViewDialog() {
        JDialog viewDialog = new JDialog(this, "View Questions", true);
        viewDialog.setSize(400, 300);
        viewDialog.setLayout(new BorderLayout());
        return viewDialog;
    }

    // EFFECTS: update the question being displayed
    private void updateQuestionDisplay(JTextArea questionArea, int index) {
        ShortAnswerQuestion question = quiz.get(index);
        questionArea.setText("Question " + question.getQuestionId() + ":\n" + question.getQuestion());
    }

    // EFFECTS: quit the application
    private void quitApplication() {
        JOptionPane.showMessageDialog(this, "Thanks for using the Quiz Reviewer app! Have a wonderful study time!");
        printEventLog();
        System.exit(0);
    }

    // // EFFECTS: removing all the things been currently typed into the text fields
    private void clearTextFields() {
        questionIdField.setText("");
        questionField.setText("");
        answerField.setText("");
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        printEventLog();
    }

    private void printEventLog() {
        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            System.out.println(event.toString());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
