project folder:
fuhui0310-project05/


Brief description of submitted files:

src/cs1C/

MillionSongDataSubset.java
    - One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.

SongEntry.java
    - One object of class SongEntry stores a simplified version of the genre data set from the Million Song Dataset.

TimeConverter.java
    - Converts duration into a string representation.

src/lazyTrees/

FoothillTunesStore.java
     * Simulates the store scenario of removing and adding tune to a store's inventory.
     * Implements BST with lazy deletion to keep track of total inventory ("deleted" + non deleted)
     * and current inventory (non deleted only).

tunes.java
     * One object of tune class represents one tune in the inventory, with two class members.
    
SuperMarket.java
     * Simulates the market scenario of buying and adding items to a store's inventory.
     * Implements BST with lazy deletion to keep track of total inventory ("deleted" + non deleted)
     * and current inventory (non deleted only).

Item.java
     * One object of Item class represents one item in the inventory, with two class members.

LazySearchTree.java
     *  BST with lazy deletion to keep track of total inventory ("deleted" + non deleted).

PrintObject.java

Traverser.java

resources/

tunes.txt
    - input for showing removal of a tune which was previously lazily removed from our LazySearchTree

tunes_additional.txt
    - input for showing removal of a tune where tree root has both left and right children

inventory_log.txt
    - input for showing an Item where user requests to add and buy many items.

inventory_short.txt
    - input for showing removal of an Item where tree root has both left and right children

inventory_invalid_removal.txt
    - input for showing removal of an Item which was previously lazily removed from our LazySearchTree.

inventory_additional.txt
    - input for showing additional test case that create unbalanced tree and remove a node which is not exist in the list.
    
RUN.txt
    - console output of DemoGit.java

README.txt
    - description of submitted files
    
docs/