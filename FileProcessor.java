import java.io.*;
import java.util.*;

public class FileProcessor {
    private String filename;

    public FileProcessor(String filename) {
        this.filename = filename;
    }

    public File openFile() {
        return new File(this.filename);
    }

    public void readFile() {
        File fileHandler = openFile();
        List<String> exam = new ArrayList<>();
        
        try (Scanner sc = new Scanner(fileHandler)) {
            while (sc.hasNextLine()) {
                exam.add(sc.nextLine());
            }

            int i = 0;
            while (sc.hasNextLine()) {
                System.out.println(exam.get(i));
                i++;
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }

        try (Scanner sc = new Scanner(fileHandler)) {
            for (int i = 0; i < exam.size(); i++) {
                System.out.println(exam.get(i));
              }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }

    }

    public void writeLine(String text) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) { 
            pw.println(text);
            pw.close();
        } catch (IOException ex) {
            System.err.println("Error writing to file: " + ex.getMessage());
        }
    }

    public String frequencyTable() {
        File fileHandler = openFile();
        HashMap<String, Integer> frequencyMap = new HashMap<>();
        StringBuilder result = new StringBuilder();
    
        try (Scanner sc = new Scanner(fileHandler)) {
            if (sc.hasNextLine()) {
                sc.nextLine(); // skip header
            }
    
            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                String[] values = text.split(",");
    
                if (values.length < 5) {
                    continue;
                }
    
                String attendance = values[0].trim();
                String job = values[1].trim();
                String submissions = values[2].trim();
                String studyhours = values[3].trim();
                String graduated = values[4].trim();
    
                String key = attendance + "\t" + job + "\t" + submissions + "\t" + studyhours + "\t" + graduated;
                frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
            }
        } catch (FileNotFoundException e) {
            return "File not found: " + fileHandler.getName();
        }
    
        result.append("\n\t\t----Frequency Table----\n");
        result.append("Attendance \tJob \tSubmissions \tStudy Hours \tGratuated\n");

    
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            double percentage = (entry.getValue() / 200.0) * 100;
            result.append(entry.getKey())
                .append("\t| ")
                .append(String.format("%.2f", percentage))
                .append("%\n");
        }
    
        return result.toString();
    }    
}



