/*
 * Description: The purpose of this class is to training and predicton using the Naive Bayes
 * Classifier algorithm. it uses the GUI to predict whether a student will graduate or not, 
 * and it checks how acurate the model is by testing it.
 * Author: Blessing Ugochukwu
 * Date: 23/04/2025
 */

import java.util.*;

public class Predictor {
    private Map<String, Integer> labelCount = new HashMap<>();
    private Map<String, Map<String, Integer>> featureCount = new HashMap<>();
    private int totalStudents = 0;

    // trains the model with a list of student objects
    public void train(List<Student> students) {
        // resets the counts
        labelCount.clear();
        featureCount.clear();

        // sets the total number of students
        totalStudents = students.size();

        for (Student s : students) {
            // gets the student's graduation status
            String label = s.getGraduated();

            // counts how many times each label appears
            Integer currentCount = labelCount.get(label);
            if (currentCount == null) {
                labelCount.put(label, 1);
            }
            else {
                labelCount.put(label, currentCount + 1);
            }

            // updates how many times feature appears
            addFeature("attendance=" + s.getAttendance(), label);
            addFeature("job=" + s.getJob(), label);
            addFeature("submissions=" + s.getSubmissions(), label);
            addFeature("studyhours=" + s.getStudyhours(), label);
        }
    }

    // method to update feature counts
    private void addFeature(String feature, String label) {
        String key = feature + "|" + label;

        // checks if the feature already exists in the featureCount map
        Map<String, Integer> labelMap = featureCount.get(key);
        if (labelMap == null) {
            labelMap = new HashMap<>();
            featureCount.put(key, labelMap);
        }

        // updates how many times the feature label pair exists
        Integer currentCount = labelMap.get(label);
        if (currentCount == null) {
            labelMap.put(label, 1);
        }
        else {
            labelMap.put(label, currentCount + 1);
        }
    }

    // method to make a prediction for a student whether they will graduate or not
    public String predict(Student s) {
        double yesProb = calcProbability("yes", s);
        double noProb = calcProbability("no", s);

        if (yesProb > noProb) {
            return "yes";
        } else {
            return "no";
        }
    }

    // method to calculate the probability that a student belongs to a specific label
    private double calcProbability(String label, Student s) {
        Integer totalLabelCount = labelCount.get(label);
        if (totalLabelCount == null) {
            totalLabelCount = 0;
        }

        double labelProb = 0;
        if (totalStudents > 0) {
            // makes value a double to avoid an outcome of zero
            labelProb = (double) totalLabelCount / totalStudents;
        }
         
        double conditionalProb = 1.0; // starts with a neutral probability

        // multiplies the probability for each feature
        conditionalProb *= getProb("attendance" + s.getAttendance(), label);
        conditionalProb *= getProb("job" + s.getJob(), label);
        conditionalProb *= getProb("submissions" + s.getSubmissions(), label);
        conditionalProb *= getProb("studyhours" + s.getStudyhours(), label);

        // returns the labal probability multiplied by all the feature probabilities
        return labelProb * conditionalProb;
    }

    // method to get the conditional probabilty of a feature given the label (yes/no)
    private double getProb(String feature, String label) {
        String key = feature + "|" + label; // unique key

        int count = 0;
        Map<String, Integer> labelMap = featureCount.get(key);
        if (labelMap != null) {
            Integer found = labelMap.get(label);
            if (found != null) {
                count = found;
            }
        }

        int labelTotal = 0;
        Integer total = labelCount.get(label);
        if (total != null) {
            labelTotal = total;
        }

        // returns to avoid 0 probability
        return (count + 1.0) / (labelTotal + 2.0);
    }
}