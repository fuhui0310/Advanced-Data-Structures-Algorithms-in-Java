package sorters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Reads the numbers from file and puts them in an array.
 * Then splits the array according to the given memory size and adds the chunks to the chunks array.
 * Created by Fu on 19/3/2018.
 */
public class SimulateChunks {
    /**
     * Reads the numbers from file and puts them in an array.
     * Then splits the array according to the given memory size and adds the chunks to the chunks array.
     * @param memSize
     * @param s
     * @param fileChunksAsArrays
     */
    public static void splitFileIntoArrayChunks(int memSize, String s, ArrayList<Integer[]> fileChunksAsArrays) {
        try {
            Scanner input = new Scanner(new File(s));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] linesOfText = line.split(",");
                Integer[] itemsArray = new Integer[linesOfText.length];
                for (int i = 0; i < itemsArray.length; i++) {
                    itemsArray[i] = Integer.parseInt(linesOfText[i]);
                }
                for (int i = 0; i < itemsArray.length / memSize; i++) {
                    Integer[] chunk = new Integer[memSize];
                    int endIndex = (i + 1) * memSize;
                    chunk = Arrays.copyOfRange(itemsArray, i * memSize, endIndex);
                    fileChunksAsArrays.add(chunk);
                }
                if ((itemsArray.length % memSize) != 0){
                    int from = (itemsArray.length / memSize) * memSize;
                    int to = from + (itemsArray.length % memSize);
                    Integer[] chunk = new Integer[(to - from + 1)];
                    chunk = Arrays.copyOfRange(itemsArray, from, to );
                    fileChunksAsArrays.add(chunk);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + s + " not found!");
        }
    }
}
