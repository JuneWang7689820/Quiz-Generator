package ui;

import javax.swing.*;
import java.awt.BorderLayout;

public class Main {

    // EFFECTS: initate the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showWelcomeMessage();
            }
        });
    }

    // EFFECTS: showing the welcome message while giving choices for the user to
    // chose between MC question or shortanser to create.
    @SuppressWarnings("methodlength")
    private static void showWelcomeMessage() {
        JFrame frame = new JFrame("Quiz Type Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());
        JLabel label = new JLabel("<html>Welcome to the Short Answer Quiz Generator!<br>"
                + "Please select the type of quiz you want to create:</html>", SwingConstants.CENTER);
        frame.add(label, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        JButton shortAnswerButton = new JButton("Short Answer Quiz");
        JButton multipleChoiceButton = new JButton("Multiple Choice Quiz");
        JButton pictureButton = new JButton("Piture Display!");
        shortAnswerButton.addActionListener(e -> {
            frame.dispose();
            new ShortAnswerQuizReviewerGUI();
        });
        multipleChoiceButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Sorry, this function is not ready; please try short answer!");
        });
        pictureButton.addActionListener(e -> {
            displayImage();
        });
        buttonPanel.add(shortAnswerButton);
        buttonPanel.add(multipleChoiceButton);
        buttonPanel.add(pictureButton);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    //EFFECTS: showing /displaying the image 
    private static void displayImage() {
        JFrame frame = new JFrame("Image Display");
        JLabel displayField;
        ImageIcon image;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            image = new ImageIcon(Main.class.getResource("Screenshot 2024-08-08 220023.png")); // Adjust path as needed
            if (image.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
                throw new Exception("Image not loaded correctly");
            }
            displayField = new JLabel(image);
            frame.add(displayField);
        } catch (Exception e) {
            System.out.println("Image cannot be found or loaded: " + e.getMessage());
        }

        frame.setSize(2000, 3000);
        frame.setVisible(true);
    }

}
