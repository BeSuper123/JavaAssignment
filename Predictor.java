import java.util.*;

public class Predictor {

    private Map<String, Integer> labelCounts = new HashMap<>(); // e.g., yes=120, no=80
    private Map<String, Map<String, Integer>> featureLabelCounts = new HashMap<>();
    private int totalExamples = 0;

    // Train the model with a list of Student objects
    public void train(List<Student> trainingData) {
        labelCounts.clear();
        featureLabelCounts.clear();
        totalExamples = trainingData.size();

        for (Student s : trainingData) {
            String label = s.getGraduated();
            labelCounts.put(label, labelCounts.getOrDefault(label, 0) + 1);

            addFeatureCount("attendance=" + s.getAttendance(), label);
            addFeatureCount("job=" + s.getJob(), label);
            addFeatureCount("submissions=" + s.getSubmissions(), label);
            addFeatureCount("studyhours=" + s.getStudyhours(), label);
        }
    }

    private void addFeatureCount(String feature, String label) {
        String key = feature + "|" + label;
        featureLabelCounts.putIfAbsent(key, new HashMap<>());
        Map<String, Integer> countMap = featureLabelCounts.get(key);
        countMap.put(label, countMap.getOrDefault(label, 0) + 1);
    }

    // Predict label based on feature values in Student
    public String predict(Student s) {
        double yesProb = calculateLabelProbability("yes", s);
        double noProb = calculateLabelProbability("no", s);

        return (yesProb > noProb) ? "yes" : "no";
    }

    private double calculateLabelProbability(String label, Student s) {
        double labelProb = (double) labelCounts.getOrDefault(label, 0) / totalExamples;
        double conditionalProb = 1.0;

        conditionalProb *= getConditionalProbability("attendance=" + s.getAttendance(), label);
        conditionalProb *= getConditionalProbability("job=" + s.getJob(), label);
        conditionalProb *= getConditionalProbability("submissions=" + s.getSubmissions(), label);
        conditionalProb *= getConditionalProbability("studyhours=" + s.getStudyhours(), label);

        return labelProb * conditionalProb;
    }

    private double getConditionalProbability(String feature, String label) {
        String key = feature + "|" + label;
        int count = featureLabelCounts.getOrDefault(key, new HashMap<>()).getOrDefault(label, 0);
        int labelTotal = labelCounts.getOrDefault(label, 0);

        // Laplace smoothing: add-one smoothing
        return (count + 1.0) / (labelTotal + 2.0);
    }
}