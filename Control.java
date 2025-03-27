/*
 * Description: the purpose of this class is to call all the objects
 * Author: Blessing Ugochukwu
 * Date: 20/03/2025
 */
import java.util.Scanner;

public class Control {
    public static void main(String[] args) {
        FileProcessor fp = new FileProcessor("data.csv");
        Scanner sc = new Scanner(System.in);
        String text;

        fp.readFile();

        System.out.println("adult or child?");
        text = sc.nextLine();
        System.out.println("male or female");
        text += "," + sc.nextLine();
        System.out.println("Is your attendance high or low");
        text += "," + sc.nextLine();
        System.out.println("what is your mode of transport [bus, car]");
        text += "," + sc.nextLine();
        System.out.println("Exam passed? [yes/no]");
        text += "," + sc.nextLine();

        fp.writeLine(text);
        fp.readFile();
        sc.close();

        fp.frequencyTable();
    }
}