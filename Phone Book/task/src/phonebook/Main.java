package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {


    //private static
    private static Scanner sc;

    public static void main(String[] args) {
        File inputFile = new File("C:\\Users\\lpapi\\IdeaData\\PHONE_BOOK\\find.txt");
        simplestSearch(inputFile, "");
    }

    private static void simplestSearch(File f, String str) {
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        System.out.println("Start searching...");
        long start = System.currentTimeMillis();
        while (sc.hasNextLine()) {
            if (sc.nextLine().contains(str)) {
                count++;
            }
        }
        long end = System.currentTimeMillis();
        showResults(count, start, end);
    }

    private static void showResults(int count, long start, long end) {
        long delta = end - start;
        int minutes = (int) (delta/60000);
        int seconds = (int) ((delta%60000)/1000);
        int milliseconds = (int) (delta%1000);
        System.out.println("Found "+count+" / 500 entries. Time taken: "+minutes+" min. "+seconds+" sec. "+milliseconds+" ms.");
    }
}
