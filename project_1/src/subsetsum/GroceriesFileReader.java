package subsetsum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * One object reads the input file one line at a time and store the price of groceries to an arraylist.
 * Created by Fu on 1/16/2018.
 */
public class GroceriesFileReader {
    private ArrayList<Double> priceOfGroceries = new ArrayList<Double>();

    /**
     * Constructs a readFile object with the specified price of Groceries from the input file.
     *
     * @param filePath The directory path of the input file
     */
    public ArrayList<Double> readFile(String filePath) {
        try {
            Scanner input = new Scanner(new File(filePath));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] linesOfText = line.split(",");
                priceOfGroceries.add(Double.parseDouble(linesOfText[1]));
            }
            return priceOfGroceries;
        } catch (FileNotFoundException e) {
            System.out.println("File " + filePath + " not found!");
            ;
        }
        return null;
    }
}

