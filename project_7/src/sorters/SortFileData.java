package sorters;

/**
 * Reads multiple plain text files, which contain unsorted numbers.
 * 
 * Sorts the input files by dividing them into multiple chunks.
 * Note: Each chunk size is determined by the size of memory.
 * 
 * Sorting is done in two phases:
 * 
 * Phase 1. Each individual chunk is sorted.
 * 
 * Phase 2. Use min heap sorting techniques to sort all chunks
 *          with respect to each other. 
 *          
 *          Suggestion: Take advantage of the logic from our heap sorting algorithm
 *          we studied in modules.
 *          
 * @author Foothill College, Bita M., Fu Hui
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cs1c.TimeConverter;

public class SortFileData
{
	/**
	 * Restricting the size of available memory to simulate large
	 * input file(s) that do not fit in memory.
	 * The size of a chunk is determined by the size of the memory.
	 */
	private static final int MEM_SIZE = 31;

	private static final boolean ENABLE_DEBUG_PHASE01 = true;
	private static final boolean ENABLE_DEBUG_PHASE02 = true;
	private static final int OUTPUT_WIDTH = 70;
	private static final String OUTPUT_SEPARATOR = "----------------------------------------------------------------------";


	/**
	 * Display the contents of a chunk.
	 * @param chunk    a subset of the data as a chunk 
	 */
	public static void displayChunkContent(Integer [] chunk)
	{
		System.out.println(OUTPUT_SEPARATOR);

		String outStr = "";
		for (int elem : chunk)
		{
			outStr += elem + ",";            
			if (outStr.length() > OUTPUT_WIDTH)
			{
				System.out.println(outStr);
				outStr = "";
			}
		}

		if (outStr != "")
			System.out.println(outStr);  // print out left left one

		System.out.println(OUTPUT_SEPARATOR);	    
	}


	/**
	 * Display the chunk number and contents.
	 * @param chunk    a subset of the data as a chunk 
	 * @param index    the position of the data with respect to original
	 */
	public static void displayFileChunk(Integer [] chunk, int index)
	{
		System.out.println("file chunk[" + index + "] with size " + chunk.length + " :");
		displayChunkContent(chunk);
	}


	/**
	 * For debugging and displaying results.
	 * Outputs the array of Integer objects.
	 * @param array    a subset of the data sorted 
	 * @param index1    the position of the data with respect to original
	 */
	public static void displaySortedChunks(
			Integer [] array, int index1)	
	{
		System.out.println("sort file chunk[" + index1 + "] with size " + array.length + ":");
		displayChunkContent(array);
		System.out.println("");
	}


	/**
	 * For debugging and displaying results.
	 * Used to output a sample number of chunks. 
	 */
	private static void displaySampleChunks(ArrayList<Integer[]> fileChunksAsArrays, int numOfChunks) 
	{		
		int numOfFileChunks = fileChunksAsArrays.size();

		// displays the chunks
		// NOTE: If there are too many chunks, alternatively vary by how much i is incremented.
		for (int i = 0; i < numOfChunks; i++)
		{
			// check if requested number of chunks to display is valid	  
			if (i < numOfFileChunks)
			{
				System.out.println();
				System.out.println("Phase 1 : Sorted file chunks " + i + ":");
				displaySortedChunks(fileChunksAsArrays.get(i), i);
			}       
		} // for all the chunks up to the requested number
	}


	public static void main(String[] args) 
	{
		//final String filePath = "resources/";	// Directory path for Mac OS X
		final String filePath = "resources\\";	// Directory path for Windows OS (i.e. Operating System)

		// Sample input files in Comma-Seperated-Value (CSV) format		
		final String [] fileNames = {"numbers01.txt", "numbers02.txt", "numbers03.txt", "numbers04.txt"};

		ArrayList<Integer[]> fileChunksAsArrays = new ArrayList<Integer[]>();

		for (String fname : fileNames)
		{
			// TODO: Reads text files and stores the data into arrays of integers chunk(s).
			//       Each chunk is represented by an array of Integers of length MEM_SIZE
			//       Adds the chunk(s) to the list of chunks called fileChunksAsArrays
			// Suggestion: Use Arrays.copyOfRange(int[] original, int from, int to)
			//       to copy a chunk found into fileChunksAsArrays
			//       for more details see: 
			//       http://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#copyOfRange(int[],%20int,%20int)
			SimulateChunks.splitFileIntoArrayChunks(MEM_SIZE, filePath + fname, fileChunksAsArrays);
		}


		// Phase 1. Sort each individual chunk ---------------------------------------
		// 
		// Note: the total size of all chunks should be the same as the total number
		// of values in each file divided by the memory size.
		int numOfChunks = fileChunksAsArrays.size();
		System.out.println("Number of arrays holding file input = " + numOfChunks);

		int chunkIndex = 0;
		for (Integer[] chunk : fileChunksAsArrays)
		{
			if (ENABLE_DEBUG_PHASE01)
			{
				displayFileChunk(chunk, chunkIndex);
				chunkIndex++;
			}

			// TODO: Pick a sorting algorithm from the modules which sorts an individual chunk in place.
			//       The sorting algorithm you should should depend on the specific size of the chunk.
			//       The sorted result is stored in the argument "chunk".
			BasicSorter.sortChunk(chunk);
		}

		// Display the result of various chunks after sorting.
		displaySampleChunks(fileChunksAsArrays, numOfChunks);


		// Phase 2. Use the min heap sorting techniques to sort all chunks ---------- 
		// 
		long startTime, estimatedTime;


		// capture start time
		startTime = System.nanoTime();

		DateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String timeStamp = format.format(new Date());
		String outputFileName = "result_using_min_heap_" + timeStamp + ".txt";

		
		// TODO: Use the array of HeapTuple objects called "heap" to hold the current
        // minimums (or alternatively maximums) .
		// In your RUN.txt file show a sample number of iterations.
		// NOTE: Assume the number of chunks can be greater than available MEM_SIZE.
		int heapSize = MEM_SIZE;
		HeapTuple[] heap = new HeapTuple[heapSize];
		
		// TODO: Use the minHeap (or alternatively maxHeap) technique we learned in
		//       modules to sort the various chunks with respect to each other and
		//       write the output to a file called "result_using_heap.txt"
		// Note: In class heapArrayMerger, we are *not* explicitly calling FHsort.heapSort.
		//
		// NOTE: When ENABLE_DEBUG_PHASE02 display the elements of heap to console at each iteration.
		//       That is when your implementation is ready to dump the heap to the output file, print
		//       the elements in heap to console. Write the elements to the output file. Finally, clear
		//       the contents of heap in preparation for the next iteration.
		HeapArrayMerger heapArrayMerger = new HeapArrayMerger();
		heapArrayMerger.mergeSortedArrays(MEM_SIZE, fileChunksAsArrays, heap, filePath + outputFileName, ENABLE_DEBUG_PHASE02);
		
		// TODO: Clone a copy of the heap. Add the clone to an array list.
		//       Provide an accessor method.
		// NOTE: This data structure is for testing purposes.
		// Suggestion: Test whether your heap is a valid min or max heap
		ArrayList<HeapTuple[]> heapsUsed = heapArrayMerger.getHeapsUsed();
		
		// stop and calculate elapsed time
		estimatedTime = System.nanoTime() - startTime;

		// report algorithm time
		System.out.println("\nAlgorithm Elapsed Time: "
				+ TimeConverter.convertTimeToString(estimatedTime) + "\n");		
	}
}
