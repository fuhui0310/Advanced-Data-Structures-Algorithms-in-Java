package cs1c;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A test file that investigates the recursion limit of arrays of various sizes.
 * Created by Fu on 23/3/2018.
 */
public class Client {

    /**
     * investigates different size arrays from 20,000 up to to 10 million with at 20 intervals
     * @param args
     */
    public static void main(String[] args){
        int arraySize;
        Integer[] intArray;
        Integer[] copiedIntArray;
        arraySize = 20000;
        while (arraySize <= 10000000) {
            intArray = new Integer[arraySize];
            copiedIntArray = new Integer[arraySize];
            for (int i = 0; i < arraySize; i++) {
                intArray[i] = (int) (Math.random() * arraySize);
            }
            FHsort quickSort;
            quickSort = new FHsort();
            long startTime, estimatedTime;
            int limit = 2;
            String CSVName = "resources/RUN/Test3/Test3_arraySize_" + arraySize + ".csv";
            String result = "Recursion limit, Runtime\n";
            while (limit <= 300) {
                for (int i = 0; i < arraySize; i++) {
                    copiedIntArray[i] = intArray[i];
                }
                startTime = System.nanoTime();
                quickSort.setRecursionLimit(limit);
                quickSort.quickSort(copiedIntArray);
                estimatedTime = System.nanoTime() - startTime;
                result += limit + "," + estimatedTime + "\n";
                System.out.println("Array size: " + arraySize + ", Recursion limit: " + limit
                        + ", Algorithm Elapsed Time: " + TimeConverter.convertTimeToString(estimatedTime));
                limit += 2;
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSVName))) {
                bw.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            arraySize += 499000;
        }
    }
}
