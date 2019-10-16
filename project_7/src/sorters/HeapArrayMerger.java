package sorters;

import cs1c.FHsort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Uses the minHeap technique to sort the various chunks with respect to each other
 * and writes the output to a file called result_using_min_heap.txt
 * Created by Fu on 19/3/2018.
 */
public class HeapArrayMerger {
    private ArrayList<HeapTuple[]> heapsUsed;

    /**
     * Uses the minHeap technique to sort the various chunks with respect to each other
     * and writes the output to a file called result_using_min_heap.txt
     * @param memSize
     * @param fileChunksAsArrays
     * @param heap
     * @param s
     * @param enableDebugPhase02
     */
    public void mergeSortedArrays(int memSize, ArrayList<Integer[]> fileChunksAsArrays, HeapTuple[] heap, String s, boolean enableDebugPhase02) {
        String result = "";
        int totalItem = 0;
        int numberOfChunks = fileChunksAsArrays.size();
        for (int i = 0; i < numberOfChunks; i++) {
            totalItem += fileChunksAsArrays.get(i).length;
        }
        int[] test = new int[totalItem];
        heapsUsed = new ArrayList<HeapTuple[]>();
        for (int i = 0; i < totalItem; i++) {
            if(numberOfChunks < memSize) {
                heap = new HeapTuple[numberOfChunks];
            }else {
                heap = new HeapTuple[memSize];
            }
            for (int j = 0; j < numberOfChunks; j++) {
                for (int k = 0; k < fileChunksAsArrays.get(j).length; k++) {
                    if (fileChunksAsArrays.get(j)[k] >= 0){
                        HeapTuple current = new HeapTuple(fileChunksAsArrays.get(j)[k],j,k);
                        if(heap[0] == null) {
                            heap[0] = current;
                            k +=fileChunksAsArrays.get(j).length;
                        }else{
                            if(heap[heap.length - 1] == null){
                                int n = 0;
                                while (heap[n] != null){
                                    n++;
                                }
                                heap[n] = current;
                                k +=fileChunksAsArrays.get(j).length;
                            }else{
                                heapSort(heap);
                                if(heap[(heap.length - 1)].compareTo(current) > 1){
                                    heap[(heap.length - 1)] = current;
                                }
                                k +=fileChunksAsArrays.get(j).length;
                            }
                        }
                    }
                }
            }
            heapSort(heap);
            result += heap[0].getData() + ",";
            test[i] = heap[0].getData();
            heapsUsed.add(heap);
            fileChunksAsArrays.get(heap[0].getArrayIndex())[heap[0].getIndexInArray()] = -1;
            if(fileChunksAsArrays.get(heap[0].getArrayIndex())[fileChunksAsArrays.get(heap[0].getArrayIndex()).length - 1] < 0){
                fileChunksAsArrays.remove(heap[0].getArrayIndex());
                numberOfChunks--;
            }
        }
        if (enableDebugPhase02) {
           int sortednumber = 0;
           for(int i = 0; i < heapsUsed.size(); i++){
               sortednumber++;
           }
           System.out.println("We have " + sortednumber + " numbers in the result.");
           for(int i = 0; i < totalItem; i++){
               if(i > 0){
                   if(test[i] < test[i - 1]){
                       break;
                   }
               }
               System.out.println(test[i]);
           }

        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(s))) {
            bw.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the list of used heaps
     * @return list of used heaps
     */
    public ArrayList<HeapTuple[]> getHeapsUsed() {
        return heapsUsed;
    }

    // heapsort and helpers -------------------------------------------
    // percolateDown()
    protected static void percolateDown(HeapTuple[] heap, int hole, int arraySize)
    {
        int child;
        HeapTuple tmp;

        for( tmp = heap[hole]; 2 * hole + 1 < arraySize; hole = child )
        {
            child = 2 * hole + 1;
            // if 2 children, get the GREATER of the two (because MAX heap)
            if( child < arraySize - 1 && heap[child].compareTo(heap[child + 1]) < 0)
                child++;
            if( tmp.compareTo(heap[child]) < 0 )   // MAX heap, not min heap
                heap[hole] = heap[child];
            else
                break;
        }
        heap[hole] = tmp;
    }

    // heapsort public driver
    public static void heapSort(HeapTuple[] heap)
    {
        int k, arraySize;
        HeapTuple temp;

        // order the array using percolate down
        arraySize = heap.length;
        for(k = arraySize/2; k >= 0; k-- )
            percolateDown(heap, k, arraySize);

        // now remove the max element (root) and place at end of array
        for(k = arraySize - 1; k > 0; k-- )
        {
            // "remove" by placing at end of array
            temp = heap[0];
            heap[0] = heap[k];
            heap[k] = temp;
            percolateDown( heap, 0, k );  // k represents the shrinking array size
        }
    }


}
