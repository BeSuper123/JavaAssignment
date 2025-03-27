/*
 * Description: the purpose of this class is to call all the objects
 * Author: Blessing Ugochukwu
 * Date: 27/03/2025
 */

public class Control {
    public static void main(String[] args) {
        FileProcessor fp = new FileProcessor("data.csv");

        MyWindow screen = new MyWindow("Frequency Table");
    }
}