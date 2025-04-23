/*
 * Description: The purpose of this class is to display all the
 * text fields and buttons on the screen and show the array list of students
 * and make a prediction using hardcoded data. It contains 4 main buttons, add 
 * student, check accuracy, train, and predict
 * Author: Blessing Ugochukwu
 * Date: 23/04/2025
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MyWindow extends JFrame {
    // attributes
    JButton addStudentButton, frequencyTableButton, predictButton, accuracyButton;
    JTextField attendance, job, submissions, studyhours, graduated;
    JTextArea displayArea;
    JLabel label1, label2, label3, label4, label5;
    ArrayList<Student> students = new ArrayList<>();

    // file data.csv was AI generated
    FileProcessor fp = new FileProcessor("data.csv");

    // constructor
    public MyWindow(String title) {
        super(title);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.BLACK);

        // added the user inputs
        attendance = new JTextField(10);
        job = new JTextField(10);
        submissions = new JTextField(10);
        studyhours = new JTextField(10);
        graduated = new JTextField(10);

        // added the buttons
        addStudentButton = new JButton("Add Student");
        frequencyTableButton = new JButton("Frequency Table");
        predictButton = new JButton("Predict");
        accuracyButton = new JButton("Check Accuracy");

        // added a display area
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);

        // labels added for the user inputs
        label1 = new JLabel("Attendance (high/low):");
        label2 = new JLabel("Job (job/nojob):");
        label3 = new JLabel("Submissions (ontime/late):");
        label4 = new JLabel("Study Hours (many/few):");
        label5 = new JLabel("Graduated (yes/no):");

        // changed the colours of the labels to white
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
        add(addStudentButton);
        add(frequencyTableButton); 
        add(predictButton);
        add(accuracyButton);
        add(new JScrollPane(displayArea));

        // changed the colour of the buttons to turquoise
        Color buttonColour = new Color(640128);
        addStudentButton.setBackground(buttonColour);
        addStudentButton.setForeground(Color.BLACK);
        frequencyTableButton.setBackground(buttonColour);
        frequencyTableButton.setForeground(Color.BLACK);
        predictButton.setBackground(buttonColour);
        predictButton.setForeground(Color.BLACK);
        accuracyButton.setBackground(buttonColour);
        accuracyButton.setForeground(Color.BLACK);

        // button listeners
        addStudentButton.addActionListener(e -> addStudent());
        frequencyTableButton.addActionListener(e -> showFrequencyTable());
        predictButton.addActionListener(e -> predict());
        accuracyButton.addActionListener(e -> checkAccuracy());

        setVisible(true);
    }

    // creating another instance of the fileprocessor class for the training data
    FileProcessor freqTable = new FileProcessor("freqTable.csv");

    private void addStudent() {
        String a = attendance.getText();
        String j = job.getText();
        String s = submissions.getText();
        String h = studyhours.getText();
        String g = graduated.getText();

        if (a.isEmpty() || j.isEmpty() || s.isEmpty() || h.isEmpty() || g.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // add student to csv file
        Student s1 = new Student(a, j, s, h, g);
        fp.writeLine(s1.toString());

        ArrayList<Student> updatedData = fp.readFile();
        Predictor p = new Predictor();
        p.train(updatedData);

        JOptionPane.showMessageDialog(this, "Student Details Added: " + s1.toString(), "Student Saved and Classifier Updated", JOptionPane.INFORMATION_MESSAGE);

        clearText();
    }

    private void clearText() {
        attendance.setText("");
        job.setText("");
        submissions.setText("");
        studyhours.setText("");
        graduated.setText("");
    }

    // method to show the frequency table on the screen
    private void showFrequencyTable() {
        displayArea.setText("");

        String table = fp.frequencyTable();
        displayArea.setText(table);
    }

    // method to show the prediction
    private void predict() {
        String a = attendance.getText();
        String j = job.getText();
        String s = submissions.getText();
        String h = studyhours.getText();

        // checks if the values are empty, and if so shows an error message
        if (a.isEmpty() || j.isEmpty() || s.isEmpty() || h.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // uses different combinations         
        displayArea.setText(freqTable.getPrediction(a, j, s, h));
    }

    private void checkAccuracy() {
        ArrayList<Student> allData = fp.readFile();

        if (allData.size() < 200) {
            displayArea.setText("Please add at least 200 rows");
        }

        ArrayList<Student> trainingSet = new ArrayList<>(allData.subList(0, 150));
        ArrayList<Student> testSet = new ArrayList<>(allData.subList(150, 200));

        Predictor p = new Predictor();
        p.train(trainingSet);

        int correct = 0;

        for (Student testStudent : testSet) {
            String predicted = p.predict(testStudent);
            if (predicted.equalsIgnoreCase(testStudent.getGraduated())) {
                correct++;
            }
        }
    
        double accuracy = (correct / 50.0) * 100;
        displayArea.setText("Model Accuracy: " + String.format("%.2f", accuracy) + "%\nCorrect Predictions: " + correct + " out of 50");
    }
}
 