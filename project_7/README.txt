project folder:
fuhui0310-project08/

Brief description of submitted files:

src/cs1c/

FHsort.java
    - The sorting algorithm

TimeConverter.java
    - Converts duration into a string representation.

src/Sorter/

Basice.java
    -Using shell sort to sorts an individual chunk in place using the static sortChunk() method
    -which receives an Integer[] object as argument.

HeapArrayMerger.java
    -Uses the minHeap technique to sort the various chunks with respect to each other
    -and writes the output to a file called result_using_min_heap.txt

HeapTuple.java
    -Helper class for sorting input chunks via minHeap.

SimulateChunks.java
    -Reads the numbers from file and puts them in an array.
    -Then splits the array according to the given memory size and adds the chunks to the chunks array.

SortFileData.java
    -Reads multiple plain text files, which contain unsorted numbers.
    -Sorts the input files by dividing them into multiple chunks.
    -Note: Each chunk size is determined by the size of memory.

resources/

numbers01.txt
    - Example test file

numbers02.txt
    - Example test file

numbers03.txt
    - Example test file

numbers04.txt
    - Example test file

result_using_bin_heap.txt
    - list of all sorted numbers with respect to each other

RUN.txt
    - console output of DemoGit.java

README.txt
    - description of submitted files
    
docs/