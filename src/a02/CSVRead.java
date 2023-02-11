package a02;

import com.opencsv.CSVReader;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.util.Scanner; // Import the Scanner class to read text files

public class CSVRead {
    public static void main(String[] args) {
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        try (FileReader fileReader = new FileReader("C:\\Users\\Marcus\\IdeaProjects\\CSIS2410_Exercises\\src\\a02\\pokemon.csv")) {
            CSVReader csvReader = new CSVReader(fileReader);
            String[] nextRecord;
            nextRecord = csvReader.readNext();

            while (nextRecord != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t"  );
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
