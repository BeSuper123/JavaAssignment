import java.util.*;

public class Predictor {
    private Map<String, Integer> labelCount = new HashMap<>();
    private Map<String, Map<String, Integer>> featureCount = new HashMap<>();
    private int totalStudents = 0;

    // trains the model with a list of student objects
    public void train(List<Student> students) {
        labelCount.clear();
        featureCount.clear();
        totalStudents = students.size();

        for (Student s : students) {
            String label = s.getGraduated();

            // counts how many times each label appears
            labelCount.merge(label, 1, Integer::sum);

            // counts how often each feature value appears with the label
            addFeature("attendance=" + s.getAttendance(), label);
            addFeature("job=" + s.getJob(), label);
            addFeature("submissions=" + s.getSubmissions(), label);
            addFeature("studyhours=" + s.getStudyhours(), label);
        }
    }

    // method to update feature counts
    private void addFeature(String feature, String label) {
        String key = feature + "|" + label;

        // creates a new map for if the key doesn't exist
        featureCount.putIfAbsent(key, new HashMap<>());

        Map<String, Integer> countMap = featureCount.get(key);
        countMap.put(label, countMap.getOrDefault(label, 0) + 1);
    }

    // method to make a prediction for a student
    public String predict(Student s) {
        double yesProb = calcProbability("yes", s);
        double noProb = calcProbability("no", s);

        if (yesProb > noProb) {
            return "yes";
        } else {
            return "no";
        }
    }

    // method to calculate the probability for a label
    private double calcProbability(String label, Student s) {
        double labelProb = (double) labelCount.getOrDefault(label, 0) / totalStudents;
        double conditionalProb = 1.0;

        conditionalProb *= getProb("attendance" + s.getAttendance(), label);
        conditionalProb *= getProb("job" + s.getJob(), label);
        conditionalProb *= getProb("submissions" + s.getSubmissions(), label);
        conditionalProb *= getProb("studyhours" + s.getStudyhours(), label);

        return labelProb * conditionalProb;
    }

    // method to get the conditional probabilty of a feature given the label (yes/no)
    private double getProb(String feature, String label) {
        String key = feature + "|" + label;

        int count = featureCount.getOrDefault(key, new HashMap<>()).getOrDefault(label, 0);
        int labelTotal = labelCount.getOrDefault(label, 0);

        // returns to avoid 0 probability
        return (count + 1.0) / (labelTotal + 2.0);
    }
}