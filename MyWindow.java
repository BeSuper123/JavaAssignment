/*
 * Description: The purpose of this class is to display all the
 * text fields and buttons on the screen and show the array list of people
 * Author: Blessing Ugochukwu
 * Date: 27/03/2025
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MyWindow extends JFrame {
    // attributes
    JButton saveButton, showAllButton, deleteAllButton;
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

        // user input
        attendance = new JTextField(10);
        job = new JTextField(10);
        submissions = new JTextField(10);
        studyhours = new JTextField(10);
        graduated = new JTextField(10);

        // set buttons
        saveButton = new JButton("Save");
        showAllButton = new JButton("Show All");
        deleteAllButton = new JButton("Delete All");

        // set display area
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);

        label1 = new JLabel("Attendance (high/low):");
        label2 = new JLabel("Job (job/nojob):");
        label3 = new JLabel("Submissions (ontime/late):");
        label4 = new JLabel("Study Hours (many/few):");
        label5 = new JLabel("Graduated (yes/no):");

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
        add(saveButton); 
        add(showAllButton); 
        add(deleteAllButton);
        add(new JScrollPane(displayArea));

        // button listeners
        saveButton.addActionListener(e -> savePerson());
        showAllButton.addActionListener(e -> showAll());
        deleteAllButton.addActionListener(e -> deleteAll());

        setVisible(true);
    }

    // method to save all entered people
    private void savePerson() {
        String a = attendance.getText();
        String j = job.getText();
        String s = submissions.getText();
        String h = studyhours.getText();
        String g = graduated.getText();

        if (a.isEmpty() || j.isEmpty() || s.isEmpty() || h.isEmpty() || g.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student s1 = new Student(a, j, s, h, g);
        personList.add(s1);

        JOptionPane.showMessageDialog(this, "Saved: " + s1.toString(), "Student Saved", JOptionPane.INFORMATION_MESSAGE);

        attendance.setText("");
        job.setText("");
        submissions.setText("");
        studyhours.setText("");
        graduated.setText("");
    }

    // method to show all entered people
    private void showAll() {
        displayArea.setText("");
        for (Student s : personList) {
            displayArea.append(s.toString() + "\n");
        }
    }

    // method to delete people from the array
    private void deleteAll() {
        if (personList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "The list is already empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete all this?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            personList.clear();
            displayArea.setText("All students have been deleted.\n");
            JOptionPane.showMessageDialog(this, "All students have been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}