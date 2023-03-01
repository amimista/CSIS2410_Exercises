package a02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PokemonDB {
    public void createDexTable(boolean drop) {
        try {
            File file = new File("src/a02/pokemon.csv");
            Scanner reader = new Scanner(file);
            ArrayList<String> rowData = new ArrayList<>();
            while(reader.hasNextLine())
                rowData.add(reader.nextLine());

            String[][] data = new String[rowData.size()][rowData.get(0).split(",").length];

            reader = new Scanner(file);

            for (int i = 0; i < data.length; i++) {
                data[i] = rowData.get(i).split(",");
                System.out.println(Arrays.toString(data[i]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PokemonDB pdb = new PokemonDB();
        pdb.createDexTable(false);
    }
}
