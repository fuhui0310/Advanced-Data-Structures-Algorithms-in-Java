project folder:
fuhui0310-project07/

Brief description of submitted files:

src/cs1c/

MillionSongDataSubset.java
    - One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.

SongEntry.java
    - One object of class SongEntry stores a simplified version of the genre data set from the Million Song Dataset.

TimeConverter.java
    - Converts duration into a string representation.

src/hashTables/

FHhashQP.java

FHhashQPFind.java
 * Implement the class FHhashQPwFind to extend FHhashQP.
 * Take a second parameter KeyType to find the item in the hash map.

HashEntry.java

MyTunes.java
 * Tests the functionality of FHhashQPwFind.java.
  * Specifically checks for implementation of find() function to return an object associated with a given key input.

SongComplnt.java
 * Wrapper class for a SongEntry object. We will use this to compares objects based on the songs int id field.

SongsCompGenre.java
 * Wrapper class for a SongEntry object. We will use this to compares objects based on the songs String genre field.
 * We will use this to determine the number of songs in each genre.

TableGenerator.java
 *  class will create and populate two hash tables of type FHhashQPwFind class, one for each wrapper class

resources/

findGenres.txt
    - Example test file for hashing

findIDs.txt
    - Example test file for hashing

findGenres_test1.txt
    - test file for testing method find() when requesting negative number.

findIDs_test1.txt
    - test file for testing method find() when requesting negative number.

findGenres_test2.txt
    - test file for testing method() when requesting 0 and 59599.

findIDs_test2.txt
    - test file for testing method() when requesting 0 and 59599.
    
RUN.txt
    - console output of DemoGit.java

README.txt
    - description of submitted files
    
docs/