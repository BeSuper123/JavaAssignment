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

    public void frequencyTable() {
        File fileHandler = openFile(); // Assuming openFile() returns a valid File object
        int childPass = 0;
        int childFail = 0;
        int adultPass = 0;
        int adultFail = 0;
    
        try (Scanner sc = new Scanner(fileHandler)) {
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
    
            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                String[] values = text.split(",");
    
                // Ensure line has the correct number of columns
                if (values.length < 5) {
                    continue;
                }
    
                String ageGroup = values[0].trim();
                String examPassed = values[4].trim();
    
                if (ageGroup.equalsIgnoreCase("Child")) {
                    if (examPassed.equalsIgnoreCase("Yes")) {
                        childPass++;
                    } else if (examPassed.equalsIgnoreCase("No")) {
                        childFail++;
                    }
                } else if (ageGroup.equalsIgnoreCase("Adult")) {
                    if (examPassed.equalsIgnoreCase("Yes")) {
                        adultPass++;
                    } else if (examPassed.equalsIgnoreCase("No")) {
                        adultFail++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileHandler.getName());
        }
    
        // Print frequency table
        System.out.println("\n----Frequency Table----");
        System.out.println("Child Pass: " + childPass);
        System.out.println("Child Fail: " + childFail);
        System.out.println("Adult Pass: " + adultPass);
        System.out.println("Adult Fail: " + adultFail);

    }
}



