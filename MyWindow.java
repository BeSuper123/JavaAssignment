/*
 * Description: The purpose of this class is to display all the
 * text fields and buttons on the screen and show the array list of people
 * and make a prediction using hardcoded rules (Level 1 Naive Bayes)
 * Author: Blessing Ugochukwu
 * Date: 27/03/2025
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MyWindow extends JFrame {
    // attributes
    JButton frequencyTableButton, predictButton;
    JTextField attendance, job, submissions, studyhours, graduated;
    JTextArea displayArea;
    JLabel label1, label2, label3, label4, label5;
    ArrayList<Student> personList = new ArrayList<>();

    // constructor
    public MyWindow(String title) {
        super(title);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.BLACK);

        // user input
        attendance = new JTextField(10);
        job = new JTextField(10);
        submissions = new JTextField(10);
        studyhours = new JTextField(10);
        graduated = new JTextField(10);

        // set buttons
        frequencyTableButton = new JButton("Frequency Table");
        predictButton = new JButton("Predict");

        // set display area
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);

        label1 = new JLabel("Attendance (high/low):");
        label2 = new JLabel("Job (job/nojob):");
        label3 = new JLabel("Submissions (ontime/late):");
        label4 = new JLabel("Study Hours (many/few):");
        label5 = new JLabel("Graduated (yes/no):");

        Color labelColor = Color.WHITE;
        label1.setForeground(labelColor);
        label2.setForeground(labelColor);
        label3.setForeground(labelColor);
        label4.setForeground(labelColor);
        label5.setForeground(labelColor);

        // adding objects to the screen
        add(label1); 
        add(attendance);
        add(label2); 
        add(job);
        add(label3); 
        add(submissions);
        add(label4); 
        add(studyhours);
        add(label5); 
        add(graduated);
        add(frequencyTableButton); 
        add(predictButton);
        add(new JScrollPane(displayArea));

        Color darkPurple = new Color(64, 0, 128);
        frequencyTableButton.setBackground(darkPurple);
        frequencyTableButton.setForeground(Color.WHITE);

        predictButton.setBackground(darkPurple);
        predictButton.setForeground(Color.WHITE);

        // button listeners
        frequencyTableButton.addActionListener(e -> showFrequencyTable());
        predictButton.addActionListener(e -> predict());

        setVisible(true);
    }

    // private void savePerson() {
    //     String a = attendance.getText();
    //     String j = job.getText();
    //     String s = submissions.getText();
    //     String h = studyhours.getText();
    //     String g = graduated.getText();

    //     if (a.isEmpty() || j.isEmpty() || s.isEmpty() || h.isEmpty() || g.isEmpty()) {
    //         JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
    //         return;
    //     }

    //     Student s1 = new Student(a, j, s, h, g);
    //     personList.add(s1);

    //     JOptionPane.showMessageDialog(this, "Saved: " + s1.toString(), "Student Saved", JOptionPane.INFORMATION_MESSAGE);

    //     attendance.setText("");
    //     job.setText("");
    //     submissions.setText("");
    //     studyhours.setText("");
    //     graduated.setText("");
    // }

    // private void showAll() {
    //     displayArea.setText("");
    //     for (Student s : personList) {
    //         displayArea.append(s.toString() + "\n");
    //     }
    // }

    private void showFrequencyTable() {
        displayArea.setText("");
        FileProcessor fp = new FileProcessor("data.csv");
        String table = fp.frequencyTable();
        displayArea.setText(table);
    }

    private void predict() {
        String a = attendance.getText();
        String j = job.getText();
        String s = submissions.getText();
        String h = studyhours.getText();

        if (a.isEmpty() || j.isEmpty() || s.isEmpty() || h.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields (except Graduated) must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String prediction;
        if (a.equals("high") && j.equals("job") && s.equals("ontime") && h.equals("many")) {
            prediction = "Prediction: YES (high chance of graduating)";
        } else if (a.equals("low") && j.equals("job") && h.equals("few")) {
            prediction = "Prediction: NO (low chance of graduating)";
        } else {
            prediction = "Prediction: MAYBE (insufficient rule match)";
        }

        displayArea.setText(prediction);
    }
}
 