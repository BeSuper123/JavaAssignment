/*
 * Description: The purpose of this class is to have all
 * the file data within, such as open file, read,  and write 
 * also having the frequency table
 * Author: Blessing Ugochukwu
 * Date: 27/03/2025
 */

import java.io.*;
import java.util.*;

public class FileProcessor {
    //atribute
    private String filename;

    //constructor
    public FileProcessor(String filename) {
        this.filename = filename;
    }

    // method to open the file
    public File openFile() {
        return new File(this.filename);
    }

    // method to read the file
    public void readFile() {
        File fileHandler = openFile();
        ArrayList<String> exam = new ArrayList<>();
        
        // adds the line to the list
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

        // prints each line
        try (Scanner sc = new Scanner(fileHandler)) {
            for (int i = 0; i < exam.size(); i++) {
                System.out.println(exam.get(i));
                }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }

    }

    // method to write into the file
    public void writeLine(String text) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) { 
            pw.println(text);
            pw.close();
        } catch (IOException ex) {
            System.err.println("Error writing to file: " + ex.getMessage());
        }
    }

    // method for the frequency table
    public String frequencyTable() {
        // opens the file
        File fileHandler = openFile();

        // creates a hash frequency map
        HashMap<String, Integer> frequencyMap = new HashMap<>();
        StringBuilder result = new StringBuilder();
    
        try (Scanner sc = new Scanner(fileHandler)) {
            if (sc.hasNextLine()) {
                sc.nextLine(); // skips header because it is not important
            }
            
            // while there is another line, sepearates each value
            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                String[] values = text.split(",");
    
                // if the value is lower than 5 it keeps going on that line
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
    
        // prints the frequency table
        result.append("\n\t\t----Frequency Table----\n");
        result.append("Attendance \tJob \tSubmissions \tStudy Hours \tGratuated\n");

        // organises the data using a hash map
        for (HashMap.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            // gets the percentage of the values in the file
            double percentage = (entry.getValue() / 200.0) * 100;
            result.append(entry.getKey())
                .append("\t| ")
                .append(String.format("%.2f", percentage))
                .append("%\n");
        }
    
        // returns the result
        return result.toString();
    }    
}



